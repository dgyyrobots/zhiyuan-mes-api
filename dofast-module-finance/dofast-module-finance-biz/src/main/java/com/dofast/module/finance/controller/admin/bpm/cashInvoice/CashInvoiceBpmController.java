package com.dofast.module.finance.controller.admin.bpm.cashInvoice;

import com.dofast.framework.common.pojo.CommonResult;
import com.dofast.framework.common.pojo.PageResult;
import com.dofast.module.finance.controller.admin.bpm.cashInvoice.vo.CashInvoiceBpmCreateReqVO;
import com.dofast.module.finance.controller.admin.bpm.cashInvoice.vo.CashInvoiceBpmPageReqVO;
import com.dofast.module.finance.controller.admin.bpm.cashInvoice.vo.CashInvoiceBpmRespVO;
import com.dofast.module.finance.convert.cash.CashInvoiceConvert;
import com.dofast.module.finance.dal.dataobject.cash.CashInvoiceDO;
import com.dofast.module.finance.service.bpm.invoice.CashInvoiceBpmService;
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

@Tag(name = "管理后台 - 财务发票信息工作流")
@RestController
@RequestMapping("/finance/bpm/cash-invoice")
@Validated
public class CashInvoiceBpmController {

    @Resource
    private CashInvoiceBpmService cashInvoiceBpmService;

    @PostMapping("/create")
    @PreAuthorize("@ss.hasPermission('bpm:oa-leave:create')")
    @Operation(summary = "创建财务退款信息")
    public CommonResult<Long> createLeave(@Valid @RequestBody CashInvoiceBpmCreateReqVO createReqVO) {
        return success(cashInvoiceBpmService.createCashInvoice(getLoginUserId(), createReqVO));
    }

    @GetMapping("/get")
    @PreAuthorize("@ss.hasPermission('bpm:oa-leave:query')")
    @Operation(summary = "获得财务退款信息")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    public CommonResult<CashInvoiceBpmRespVO> getLeave(@RequestParam("id") Long id) {
        CashInvoiceDO cashInvoiceDO = cashInvoiceBpmService.getCashInvoice(id);
        return success(CashInvoiceConvert.INSTANCE.convertBpm(cashInvoiceDO));
    }

    @GetMapping("/page")
    @PreAuthorize("@ss.hasPermission('bpm:oa-leave:query')")
    @Operation(summary = "获得财务退款信息分页")
    public CommonResult<PageResult<CashInvoiceBpmRespVO>> getLeavePage(@Valid CashInvoiceBpmPageReqVO pageVO) {
        PageResult<CashInvoiceDO> pageResult = cashInvoiceBpmService.getCashInvoicePage(getLoginUserId(), pageVO);
        return success(CashInvoiceConvert.INSTANCE.convertPageBpm(pageResult));
    }
}
