package com.dofast.module.purchase.controller.admin.bpm.invoice;

import com.dofast.framework.common.pojo.CommonResult;
import com.dofast.framework.common.pojo.PageResult;
import com.dofast.module.purchase.controller.admin.bpm.invoice.vo.InvoiceBpmCreateReqVO;
import com.dofast.module.purchase.controller.admin.bpm.invoice.vo.InvoiceBpmPageReqVO;
import com.dofast.module.purchase.controller.admin.bpm.invoice.vo.InvoiceBpmRespVO;
import com.dofast.module.purchase.controller.admin.invoice.vo.*;
import com.dofast.module.purchase.convert.invoice.InvoiceConvert;
import com.dofast.module.purchase.dal.dataobject.invoice.InvoiceDO;
import com.dofast.module.purchase.service.bpm.invoice.InvoiceBpmService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

import static com.dofast.framework.common.pojo.CommonResult.success;
import static com.dofast.framework.security.core.util.SecurityFrameworkUtils.getLoginUserId;

@Tag(name = "管理后台 - 采购入库工作流")
@RestController
@RequestMapping("/purchase/bpm/invoice")
@Validated
public class InvoiceBpmController {
    @Resource
    private InvoiceBpmService invoiceService;

    @PostMapping("/create")
    @PreAuthorize("@ss.hasPermission('bpm:oa-leave:create')")
    @Operation(summary = "创建采购入库信息")
    public CommonResult<Long> createLeave(@Valid @RequestBody InvoiceBpmCreateReqVO createReqVO) {
        return success(invoiceService.createInvoice(getLoginUserId(), createReqVO));
    }

    @GetMapping("/get")
    @PreAuthorize("@ss.hasPermission('bpm:oa-leave:query')")
    @Operation(summary = "获得采购入库信息")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    public CommonResult<InvoiceBpmRespVO> getLeave(@RequestParam("id") Long id) {
        InvoiceDO leave = invoiceService.getInvoice(id);
        return success(InvoiceConvert.INSTANCE.convertBpm(leave));
    }

    @GetMapping("/page")
    @PreAuthorize("@ss.hasPermission('bpm:oa-leave:query')")
    @Operation(summary = "获得采购入库信息分页")
    public CommonResult<PageResult<InvoiceBpmRespVO>> getLeavePage(@Valid InvoiceBpmPageReqVO pageVO) {
        PageResult<InvoiceDO> pageResult = invoiceService.getInvoicePage(getLoginUserId(), pageVO);
        return success(InvoiceConvert.INSTANCE.convertPageBpm(pageResult));
    }
}
