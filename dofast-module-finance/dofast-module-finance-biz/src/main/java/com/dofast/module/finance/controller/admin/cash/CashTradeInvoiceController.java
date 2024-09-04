package com.dofast.module.finance.controller.admin.cash;

import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;

import javax.validation.constraints.*;
import javax.validation.*;
import javax.servlet.http.*;
import java.util.*;
import java.io.IOException;

import com.dofast.framework.common.pojo.PageResult;
import com.dofast.framework.common.pojo.CommonResult;
import static com.dofast.framework.common.pojo.CommonResult.success;

import com.dofast.framework.excel.core.util.ExcelUtils;

import com.dofast.framework.operatelog.core.annotations.OperateLog;
import static com.dofast.framework.operatelog.core.enums.OperateTypeEnum.*;

import com.dofast.module.finance.controller.admin.cash.vo.*;
import com.dofast.module.finance.dal.dataobject.cash.CashTradeInvoiceDO;
import com.dofast.module.finance.convert.cash.CashTradeInvoiceConvert;
import com.dofast.module.finance.service.cash.CashTradeInvoiceService;

@Tag(name = "管理后台 - 业务发票关联")
@RestController
@RequestMapping("/finance/cash-trade-invoice")
@Validated
public class CashTradeInvoiceController {

    @Resource
    private CashTradeInvoiceService cashTradeInvoiceService;

    @PostMapping("/create")
    @Operation(summary = "创建业务发票关联")
    @PreAuthorize("@ss.hasPermission('finance:cash-trade-invoice:create')")
    public CommonResult<Long> createCashTradeInvoice(@Valid @RequestBody CashTradeInvoiceCreateReqVO createReqVO) {
        return success(cashTradeInvoiceService.createCashTradeInvoice(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新业务发票关联")
    @PreAuthorize("@ss.hasPermission('finance:cash-trade-invoice:update')")
    public CommonResult<Boolean> updateCashTradeInvoice(@Valid @RequestBody CashTradeInvoiceUpdateReqVO updateReqVO) {
        cashTradeInvoiceService.updateCashTradeInvoice(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除业务发票关联")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('finance:cash-trade-invoice:delete')")
    public CommonResult<Boolean> deleteCashTradeInvoice(@RequestParam("id") Long id) {
        cashTradeInvoiceService.deleteCashTradeInvoice(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得业务发票关联")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('finance:cash-trade-invoice:query')")
    public CommonResult<CashTradeInvoiceRespVO> getCashTradeInvoice(@RequestParam("id") Long id) {
        CashTradeInvoiceDO cashTradeInvoice = cashTradeInvoiceService.getCashTradeInvoice(id);
        return success(CashTradeInvoiceConvert.INSTANCE.convert(cashTradeInvoice));
    }

    @GetMapping("/list")
    @Operation(summary = "获得业务发票关联列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('finance:cash-trade-invoice:query')")
    public CommonResult<List<CashTradeInvoiceRespVO>> getCashTradeInvoiceList(@RequestParam("ids") Collection<Long> ids) {
        List<CashTradeInvoiceDO> list = cashTradeInvoiceService.getCashTradeInvoiceList(ids);
        return success(CashTradeInvoiceConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得业务发票关联分页")
    @PreAuthorize("@ss.hasPermission('finance:cash-trade-invoice:query')")
    public CommonResult<PageResult<CashTradeInvoiceRespVO>> getCashTradeInvoicePage(@Valid CashTradeInvoicePageReqVO pageVO) {
        PageResult<CashTradeInvoiceDO> pageResult = cashTradeInvoiceService.getCashTradeInvoicePage(pageVO);
        return success(CashTradeInvoiceConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出业务发票关联 Excel")
    @PreAuthorize("@ss.hasPermission('finance:cash-trade-invoice:export')")
    @OperateLog(type = EXPORT)
    public void exportCashTradeInvoiceExcel(@Valid CashTradeInvoiceExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<CashTradeInvoiceDO> list = cashTradeInvoiceService.getCashTradeInvoiceList(exportReqVO);
        // 导出 Excel
        List<CashTradeInvoiceExcelVO> datas = CashTradeInvoiceConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "业务发票关联.xls", "数据", CashTradeInvoiceExcelVO.class, datas);
    }

}
