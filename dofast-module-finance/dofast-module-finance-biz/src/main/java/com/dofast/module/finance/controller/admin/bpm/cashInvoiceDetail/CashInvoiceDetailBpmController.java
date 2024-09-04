package com.dofast.module.finance.controller.admin.bpm.cashInvoiceDetail;

import com.dofast.framework.common.pojo.CommonResult;
import com.dofast.framework.common.pojo.PageResult;
import com.dofast.module.finance.controller.admin.bpm.cashInvoiceDetail.vo.CashInvoiceDetailBpmCreateReqVO;
import com.dofast.module.finance.controller.admin.bpm.cashInvoiceDetail.vo.CashInvoiceDetailBpmPageReqVO;
import com.dofast.module.finance.controller.admin.bpm.cashInvoiceDetail.vo.CashInvoiceDetailBpmRespVO;
import com.dofast.module.finance.convert.cash.CashInvoiceDetailConvert;
import com.dofast.module.finance.dal.dataobject.cash.CashInvoiceDetailDO;
import com.dofast.module.finance.service.bpm.cashInvoiceDetail.CashInvoiceDetailBpmService;
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

@Tag(name = "管理后台 - 财务发票明细工作流")
@RestController
@RequestMapping("/finance/bpm/cash-invoice-detail")
@Validated
public class CashInvoiceDetailBpmController {

    @Resource
    private CashInvoiceDetailBpmService cashInvoiceDetailBpmService;

    @PostMapping("/create")
    @PreAuthorize("@ss.hasPermission('bpm:oa-leave:create')")
    @Operation(summary = "创建财务发票明细信息")
    public CommonResult<Long> createLeave(@Valid @RequestBody CashInvoiceDetailBpmCreateReqVO createReqVO) {
        return success(cashInvoiceDetailBpmService.createCashInvoiceDetail(getLoginUserId(), createReqVO));
    }

    @GetMapping("/get")
    @PreAuthorize("@ss.hasPermission('bpm:oa-leave:query')")
    @Operation(summary = "获得财务发票明细信息")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    public CommonResult<CashInvoiceDetailBpmRespVO> getLeave(@RequestParam("id") Long id) {
        CashInvoiceDetailDO cashInvoiceDetail = cashInvoiceDetailBpmService.getCashInvoiceDetail(id);
        return success(CashInvoiceDetailConvert.INSTANCE.convertBpm(cashInvoiceDetail));
    }

    @GetMapping("/page")
    @PreAuthorize("@ss.hasPermission('bpm:oa-leave:query')")
    @Operation(summary = "获得财务发票明细信息分页")
    public CommonResult<PageResult<CashInvoiceDetailBpmRespVO>> getLeavePage(@Valid CashInvoiceDetailBpmPageReqVO pageVO) {
        PageResult<CashInvoiceDetailDO> pageResult = cashInvoiceDetailBpmService.getCashInvoiceDetailPage(getLoginUserId(), pageVO);
        return success(CashInvoiceDetailConvert.INSTANCE.convertPageBpm(pageResult));
    }
}
