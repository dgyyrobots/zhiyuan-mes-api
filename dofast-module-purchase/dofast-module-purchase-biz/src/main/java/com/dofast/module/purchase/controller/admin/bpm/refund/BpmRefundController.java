package com.dofast.module.purchase.controller.admin.bpm.refund;

import com.dofast.framework.common.pojo.CommonResult;
import com.dofast.framework.common.pojo.PageResult;
import com.dofast.module.purchase.controller.admin.bpm.refund.vo.RefundBpmCreateReqVO;
import com.dofast.module.purchase.controller.admin.bpm.refund.vo.RefundBpmPageReqVO;
import com.dofast.module.purchase.controller.admin.bpm.refund.vo.RefundBpmRespVO;
import com.dofast.module.purchase.convert.refund.RefundConvert;
import com.dofast.module.purchase.service.bpm.refund.RefundBpmService;
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

@Tag(name = "管理后台 - 采购退货工作流")
@RestController
    @RequestMapping("/purchase/bpm/refund")
@Validated
public class BpmRefundController {


    @Resource
    private RefundBpmService refundBpmService;

    @PostMapping("/create")
    @PreAuthorize("@ss.hasPermission('bpm:oa-leave:create')")
    @Operation(summary = "创建采购退货信息")
    public CommonResult<Long> createLeave(@Valid @RequestBody RefundBpmCreateReqVO createReqVO) {
        return success(refundBpmService.createRefund(getLoginUserId(), createReqVO));
    }

    @GetMapping("/get")
    @PreAuthorize("@ss.hasPermission('bpm:oa-leave:query')")
    @Operation(summary = "获得采购退货信息")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    public CommonResult<RefundBpmRespVO> getLeave(@RequestParam("id") Long id) {
        com.dofast.module.purchase.dal.dataobject.refund.RefundDO refundDO = refundBpmService.getRefundDO(id);
        return success(RefundConvert.INSTANCE.convertBpm(refundDO));
    }

    @GetMapping("/page")
    @PreAuthorize("@ss.hasPermission('bpm:oa-leave:query')")
    @Operation(summary = "获得采购退货信息分页")
    public CommonResult<PageResult<RefundBpmRespVO>> getLeavePage(@Valid RefundBpmPageReqVO pageVO) {
        PageResult<com.dofast.module.purchase.dal.dataobject.refund.RefundDO> pageResult = refundBpmService.getRefundDOPage(getLoginUserId(), pageVO);
        return success(RefundConvert.INSTANCE.convertPageBpm(pageResult));
    }
}
