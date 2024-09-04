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
import com.dofast.module.finance.dal.dataobject.cash.CashInvoiceDetailDO;
import com.dofast.module.finance.convert.cash.CashInvoiceDetailConvert;
import com.dofast.module.finance.service.cash.CashInvoiceDetailService;

@Tag(name = "管理后台 - 财务发票明细")
@RestController
@RequestMapping("/finance/cash-invoice-detail")
@Validated
public class CashInvoiceDetailController {

    @Resource
    private CashInvoiceDetailService cashInvoiceDetailService;

    @PostMapping("/create")
    @Operation(summary = "创建财务发票明细")
    @PreAuthorize("@ss.hasPermission('finance:cash-invoice-detail:create')")
    public CommonResult<Long> createCashInvoiceDetail(@Valid @RequestBody CashInvoiceDetailCreateReqVO createReqVO) {
        return success(cashInvoiceDetailService.createCashInvoiceDetail(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新财务发票明细")
    @PreAuthorize("@ss.hasPermission('finance:cash-invoice-detail:update')")
    public CommonResult<Boolean> updateCashInvoiceDetail(@Valid @RequestBody CashInvoiceDetailUpdateReqVO updateReqVO) {
        cashInvoiceDetailService.updateCashInvoiceDetail(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除财务发票明细")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('finance:cash-invoice-detail:delete')")
    public CommonResult<Boolean> deleteCashInvoiceDetail(@RequestParam("id") Long id) {
        cashInvoiceDetailService.deleteCashInvoiceDetail(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得财务发票明细")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('finance:cash-invoice-detail:query')")
    public CommonResult<CashInvoiceDetailRespVO> getCashInvoiceDetail(@RequestParam("id") Long id) {
        CashInvoiceDetailDO cashInvoiceDetail = cashInvoiceDetailService.getCashInvoiceDetail(id);
        return success(CashInvoiceDetailConvert.INSTANCE.convert(cashInvoiceDetail));
    }

    @GetMapping("/list")
    @Operation(summary = "获得财务发票明细列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('finance:cash-invoice-detail:query')")
    public CommonResult<List<CashInvoiceDetailRespVO>> getCashInvoiceDetailList(@RequestParam("ids") Collection<Long> ids) {
        List<CashInvoiceDetailDO> list = cashInvoiceDetailService.getCashInvoiceDetailList(ids);
        return success(CashInvoiceDetailConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得财务发票明细分页")
    @PreAuthorize("@ss.hasPermission('finance:cash-invoice-detail:query')")
    public CommonResult<PageResult<CashInvoiceDetailRespVO>> getCashInvoiceDetailPage(@Valid CashInvoiceDetailPageReqVO pageVO) {
        PageResult<CashInvoiceDetailDO> pageResult = cashInvoiceDetailService.getCashInvoiceDetailPage(pageVO);
        return success(CashInvoiceDetailConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出财务发票明细 Excel")
    @PreAuthorize("@ss.hasPermission('finance:cash-invoice-detail:export')")
    @OperateLog(type = EXPORT)
    public void exportCashInvoiceDetailExcel(@Valid CashInvoiceDetailExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<CashInvoiceDetailDO> list = cashInvoiceDetailService.getCashInvoiceDetailList(exportReqVO);
        // 导出 Excel
        List<CashInvoiceDetailExcelVO> datas = CashInvoiceDetailConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "财务发票明细.xls", "数据", CashInvoiceDetailExcelVO.class, datas);
    }

}
