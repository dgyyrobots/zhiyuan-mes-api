package com.dofast.module.purchase.controller.admin.invoice;

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

import com.dofast.module.purchase.controller.admin.invoice.vo.*;
import com.dofast.module.purchase.dal.dataobject.invoice.InvoiceDO;
import com.dofast.module.purchase.convert.invoice.InvoiceConvert;
import com.dofast.module.purchase.service.invoice.InvoiceService;

@Tag(name = "管理后台 - 采购入库单")
@RestController
@RequestMapping("/purchase/invoice")
@Validated
public class InvoiceController {

    @Resource
    private InvoiceService invoiceService;

    @PostMapping("/create")
    @Operation(summary = "创建采购入库单")
    @PreAuthorize("@ss.hasPermission('purchase:invoice:create')")
    public CommonResult<Integer> createInvoice(@Valid @RequestBody InvoiceCreateReqVO createReqVO) {
        return success(invoiceService.createInvoice(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新采购入库单")
    @PreAuthorize("@ss.hasPermission('purchase:invoice:update')")
    public CommonResult<Boolean> updateInvoice(@Valid @RequestBody InvoiceUpdateReqVO updateReqVO) {
        invoiceService.updateInvoice(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除采购入库单")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('purchase:invoice:delete')")
    public CommonResult<Boolean> deleteInvoice(@RequestParam("id") Integer id) {
        invoiceService.deleteInvoice(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得采购入库单")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('purchase:invoice:query')")
    public CommonResult<InvoiceRespVO> getInvoice(@RequestParam("id") Integer id) {
        InvoiceDO invoice = invoiceService.getInvoice(id);
        return success(InvoiceConvert.INSTANCE.convert(invoice));
    }

    @GetMapping("/list")
    @Operation(summary = "获得采购入库单列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('purchase:invoice:query')")
    public CommonResult<List<InvoiceRespVO>> getInvoiceList(@RequestParam("ids") Collection<Integer> ids) {
        List<InvoiceDO> list = invoiceService.getInvoiceList(ids);
        return success(InvoiceConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得采购入库单分页")
    @PreAuthorize("@ss.hasPermission('purchase:invoice:query')")
    public CommonResult<PageResult<InvoiceRespVO>> getInvoicePage(@Valid InvoicePageReqVO pageVO) {
        PageResult<InvoiceDO> pageResult = invoiceService.getInvoicePage(pageVO);
        return success(InvoiceConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出采购入库单 Excel")
    @PreAuthorize("@ss.hasPermission('purchase:invoice:export')")
    @OperateLog(type = EXPORT)
    public void exportInvoiceExcel(@Valid InvoiceExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<InvoiceDO> list = invoiceService.getInvoiceList(exportReqVO);
        // 导出 Excel
        List<InvoiceExcelVO> datas = InvoiceConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "采购入库单.xls", "数据", InvoiceExcelVO.class, datas);
    }

}
