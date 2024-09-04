package com.dofast.module.trade.controller.admin.bpm.mixin;

import com.dofast.framework.common.pojo.CommonResult;
import com.dofast.framework.common.pojo.PageResult;
import com.dofast.module.trade.controller.admin.bpm.mixin.vo.MixinOrderBpmCreateReqVO;
import com.dofast.module.trade.controller.admin.bpm.mixin.vo.MixinOrderBpmPageReqVO;
import com.dofast.module.trade.controller.admin.bpm.mixin.vo.MixinOrderBpmRespVO;
import com.dofast.module.trade.controller.admin.mixin.vo.MixinOrderPageReqVO;
import com.dofast.module.trade.convert.mixin.MixinOrderConvert;
import com.dofast.module.trade.dal.dataobject.mixin.MixinOrderDO;
import com.dofast.module.trade.service.bpm.BpmMixinOrderService;
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

/**
 * 销售订单 Controller，用于演示自己存储数据，接入工作流的例子
 *
 * @author jason
 * @author 芋道源码
 */
@Tag(name = "管理后台 - 销售订单")
@RestController
@RequestMapping("/trade/bpm/mixin-order")
@Validated
public class MixinOrderBpmController {


    @Resource
    private BpmMixinOrderService bpmMixinOrderService;

    @PostMapping("/create")
    @PreAuthorize("@ss.hasPermission('bpm:oa-leave:create')")
    @Operation(summary = "创建售后")
    public CommonResult<Long> createLeave(@Valid @RequestBody MixinOrderBpmCreateReqVO createReqVO) {
        return success(bpmMixinOrderService.createMixinOrder(getLoginUserId(), createReqVO));
    }

    @GetMapping("/get")
    @PreAuthorize("@ss.hasPermission('bpm:oa-leave:query')")
    @Operation(summary = "获得售后")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    public CommonResult<MixinOrderBpmRespVO> getLeave(@RequestParam("id") Long id) {
        MixinOrderDO leave = bpmMixinOrderService.getMixinOrder(id);
        return success(MixinOrderConvert.INSTANCE.convertBpm(leave));
    }

    @GetMapping("/page")
    @PreAuthorize("@ss.hasPermission('bpm:oa-leave:query')")
    @Operation(summary = "获得售后分页")
    public CommonResult<PageResult<MixinOrderBpmRespVO>> getLeavePage(@Valid MixinOrderBpmPageReqVO pageVO) {
        PageResult<MixinOrderDO> pageResult = bpmMixinOrderService.getMixinOrderPage(getLoginUserId(), pageVO);
        return success(MixinOrderConvert.INSTANCE.convertPageBpm(pageResult));
    }
}
