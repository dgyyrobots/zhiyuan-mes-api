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
import com.dofast.module.finance.dal.dataobject.cash.CashInvoiceDO;
import com.dofast.module.finance.convert.cash.CashInvoiceConvert;
import com.dofast.module.finance.service.cash.CashInvoiceService;

@Tag(name = "管理后台 - 发票信息")
@RestController
@RequestMapping("/finance/cash-invoice")
@Validated
public class CashInvoiceController {

    @Resource
    private CashInvoiceService cashInvoiceService;

    @PostMapping("/create")
    @Operation(summary = "创建发票信息")
    @PreAuthorize("@ss.hasPermission('finance:cash-invoice:create')")
    public CommonResult<Long> createCashInvoice(@Valid @RequestBody CashInvoiceCreateReqVO createReqVO) {
        return success(cashInvoiceService.createCashInvoice(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新发票信息")
    @PreAuthorize("@ss.hasPermission('finance:cash-invoice:update')")
    public CommonResult<Boolean> updateCashInvoice(@Valid @RequestBody CashInvoiceUpdateReqVO updateReqVO) {
        cashInvoiceService.updateCashInvoice(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除发票信息")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('finance:cash-invoice:delete')")
    public CommonResult<Boolean> deleteCashInvoice(@RequestParam("id") Long id) {
        cashInvoiceService.deleteCashInvoice(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得发票信息")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('finance:cash-invoice:query')")
    public CommonResult<CashInvoiceRespVO> getCashInvoice(@RequestParam("id") Long id) {
        CashInvoiceDO cashInvoice = cashInvoiceService.getCashInvoice(id);
        return success(CashInvoiceConvert.INSTANCE.convert(cashInvoice));
    }

    @GetMapping("/list")
    @Operation(summary = "获得发票信息列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('finance:cash-invoice:query')")
    public CommonResult<List<CashInvoiceRespVO>> getCashInvoiceList(@RequestParam("ids") Collection<Long> ids) {
        List<CashInvoiceDO> list = cashInvoiceService.getCashInvoiceList(ids);
        return success(CashInvoiceConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得发票信息分页")
    @PreAuthorize("@ss.hasPermission('finance:cash-invoice:query')")
    public CommonResult<PageResult<CashInvoiceRespVO>> getCashInvoicePage(@Valid CashInvoicePageReqVO pageVO) {
        PageResult<CashInvoiceDO> pageResult = cashInvoiceService.getCashInvoicePage(pageVO);
        return success(CashInvoiceConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出发票信息 Excel")
    @PreAuthorize("@ss.hasPermission('finance:cash-invoice:export')")
    @OperateLog(type = EXPORT)
    public void exportCashInvoiceExcel(@Valid CashInvoiceExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<CashInvoiceDO> list = cashInvoiceService.getCashInvoiceList(exportReqVO);
        // 导出 Excel
        List<CashInvoiceExcelVO> datas = CashInvoiceConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "发票信息.xls", "数据", CashInvoiceExcelVO.class, datas);
    }

}
