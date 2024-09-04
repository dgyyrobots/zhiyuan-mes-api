package com.dofast.module.purchase.controller.admin.order;

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

import com.dofast.module.purchase.controller.admin.order.vo.*;
import com.dofast.module.purchase.dal.dataobject.order.OrderDO;
import com.dofast.module.purchase.convert.order.OrderConvert;
import com.dofast.module.purchase.service.order.OrderService;

@Tag(name = "管理后台 - 采购订单")
@RestController
@RequestMapping("/purchase/order")
@Validated
public class PurchaseOrderController {

    @Resource
    private OrderService orderService;

    @PostMapping("/create")
    @Operation(summary = "创建采购订单")
    @PreAuthorize("@ss.hasPermission('purchase:order:create')")
    public CommonResult<Integer> createOrder(@Valid @RequestBody OrderCreateReqVO createReqVO) {
        return success(orderService.createOrder(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新采购订单")
    @PreAuthorize("@ss.hasPermission('purchase:order:update')")
    public CommonResult<Boolean> updateOrder(@Valid @RequestBody OrderUpdateReqVO updateReqVO) {
        orderService.updateOrder(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除采购订单")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('purchase:order:delete')")
    public CommonResult<Boolean> deleteOrder(@RequestParam("id") Integer id) {
        orderService.deleteOrder(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得采购订单")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('purchase:order:query')")
    public CommonResult<OrderRespVO> getOrder(@RequestParam("id") Integer id) {
        OrderDO order = orderService.getOrder(id);
        return success(OrderConvert.INSTANCE.convert(order));
    }

    @GetMapping("/list")
    @Operation(summary = "获得采购订单列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('purchase:order:query')")
    public CommonResult<List<OrderRespVO>> getOrderList(@RequestParam("ids") Collection<Integer> ids) {
        List<OrderDO> list = orderService.getOrderList(ids);
        return success(OrderConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得采购订单分页")
    @PreAuthorize("@ss.hasPermission('purchase:order:query')")
    public CommonResult<PageResult<OrderRespVO>> getOrderPage(@Valid OrderPageReqVO pageVO) {
        PageResult<OrderDO> pageResult = orderService.getOrderPage(pageVO);
        return success(OrderConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出采购订单 Excel")
    @PreAuthorize("@ss.hasPermission('purchase:order:export')")
    @OperateLog(type = EXPORT)
    public void exportOrderExcel(@Valid OrderExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<OrderDO> list = orderService.getOrderList(exportReqVO);
        // 导出 Excel
        List<OrderExcelVO> datas = OrderConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "采购订单.xls", "数据", OrderExcelVO.class, datas);
    }

}
