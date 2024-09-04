package com.dofast.module.sales.controller.admin.bpm;

import com.dofast.framework.common.pojo.CommonResult;
import com.dofast.framework.common.pojo.PageResult;
import com.dofast.module.sales.controller.admin.bpm.vo.OrderAfterBpmCreateReqVO;
import com.dofast.module.sales.controller.admin.bpm.vo.OrderAfterBpmPageReqVO;
import com.dofast.module.sales.controller.admin.bpm.vo.OrderAfterBpmRespVO;
import com.dofast.module.sales.controller.admin.bpm.vo.OrderAfterBpmUpdateReqVO;
import com.dofast.module.sales.controller.admin.orderafter.vo.OrderAfterUpdateReqVO;
import com.dofast.module.sales.convert.orderafter.OrderAfterConvert;
import com.dofast.module.sales.dal.dataobject.orderafter.OrderAfterDO;
import com.dofast.module.sales.service.bpm.BpmSalesService;
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
 * 售后 Controller，用于演示自己存储数据，接入工作流的例子
 *
 * @author jason
 * @author 芋道源码
 */
@Tag(name = "管理后台 - 售后工作流")
@RestController
@RequestMapping("/bpm/sales")
@Validated
public class BpmSalesController {

    @Resource
    private BpmSalesService bpmSalesService;

    @PostMapping("/create")
    @PreAuthorize("@ss.hasPermission('bpm:oa-leave:create')")
    @Operation(summary = "创建售后")
    public CommonResult<Long> createLeave(@Valid @RequestBody OrderAfterBpmCreateReqVO createReqVO) {
        return success(bpmSalesService.createSales(getLoginUserId(), createReqVO));
    }

    @GetMapping("/get")
    @PreAuthorize("@ss.hasPermission('bpm:oa-leave:query')")
    @Operation(summary = "获得售后")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    public CommonResult<OrderAfterBpmRespVO> getLeave(@RequestParam("id") Long id) {
        OrderAfterDO leave = bpmSalesService.getSales(id);
        return success(OrderAfterConvert.INSTANCE.convertBpm(leave));
    }


    @GetMapping("/page")
    @PreAuthorize("@ss.hasPermission('bpm:oa-leave:query')")
    @Operation(summary = "获得售后分页")
    public CommonResult<PageResult<OrderAfterBpmRespVO>> getLeavePage(@Valid OrderAfterBpmPageReqVO pageVO) {
        PageResult<OrderAfterDO> pageResult = bpmSalesService.getSalesPage(getLoginUserId(), pageVO);
        return success(OrderAfterConvert.INSTANCE.convertPageBpm(pageResult));
    }

}
