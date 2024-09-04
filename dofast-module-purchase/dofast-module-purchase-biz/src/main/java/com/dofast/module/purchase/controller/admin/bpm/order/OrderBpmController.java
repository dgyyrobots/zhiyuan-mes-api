package com.dofast.module.purchase.controller.admin.bpm.order;

import com.dofast.framework.common.pojo.CommonResult;
import com.dofast.framework.common.pojo.PageResult;
import com.dofast.module.purchase.controller.admin.bpm.invoice.vo.InvoiceBpmRespVO;
import com.dofast.module.purchase.controller.admin.bpm.order.vo.OrderBpmCreateReqVO;
import com.dofast.module.purchase.controller.admin.bpm.order.vo.OrderBpmPageReqVO;
import com.dofast.module.purchase.controller.admin.bpm.order.vo.OrderBpmRespVO;
import com.dofast.module.purchase.controller.admin.invoice.vo.InvoiceCreateReqVO;
import com.dofast.module.purchase.controller.admin.invoice.vo.InvoicePageReqVO;
import com.dofast.module.purchase.controller.admin.invoice.vo.InvoiceRespVO;
import com.dofast.module.purchase.controller.admin.order.vo.OrderCreateReqVO;
import com.dofast.module.purchase.controller.admin.order.vo.OrderPageReqVO;
import com.dofast.module.purchase.controller.admin.order.vo.OrderRespVO;
import com.dofast.module.purchase.convert.invoice.InvoiceConvert;
import com.dofast.module.purchase.convert.order.OrderConvert;
import com.dofast.module.purchase.dal.dataobject.invoice.InvoiceDO;
import com.dofast.module.purchase.dal.dataobject.order.OrderDO;
import com.dofast.module.purchase.service.bpm.order.OrderBpmService;
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

@Tag(name = "管理后台 - 采购订单工作流")
@RestController
@RequestMapping("/purchase/bpm/order")
@Validated
public class OrderBpmController {

    @Resource
    private OrderBpmService orderService;

    @PostMapping("/create")
    @PreAuthorize("@ss.hasPermission('bpm:oa-leave:create')")
    @Operation(summary = "创建采购订单信息")
    public CommonResult<Long> createLeave(@Valid @RequestBody OrderBpmCreateReqVO createReqVO) {
        return success(orderService.createOrder(getLoginUserId(), createReqVO));
    }

    @GetMapping("/get")
    @PreAuthorize("@ss.hasPermission('bpm:oa-leave:query')")
    @Operation(summary = "获得采购订单信息")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    public CommonResult<InvoiceBpmRespVO> getLeave(@RequestParam("id") Long id) {
        OrderDO order = orderService.getOrder(id);
        return success(OrderConvert.INSTANCE.convertBpm(order));
    }

    @GetMapping("/page")
    @PreAuthorize("@ss.hasPermission('bpm:oa-leave:query')")
    @Operation(summary = "获得采购订单信息分页")
    public CommonResult<PageResult<OrderBpmRespVO>> getLeavePage(@Valid OrderBpmPageReqVO pageVO) {
        PageResult<OrderDO> pageResult = orderService.getOrderPage(getLoginUserId(), pageVO);
        return success(OrderConvert.INSTANCE.convertPageBpm(pageResult));
    }
}
