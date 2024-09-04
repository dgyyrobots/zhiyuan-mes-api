package com.dofast.module.trade.controller.admin.order;

import cn.hutool.core.collection.CollUtil;
import com.dofast.framework.common.pojo.CommonResult;
import com.dofast.framework.common.pojo.PageResult;
import com.dofast.framework.excel.core.util.ExcelUtils;
import com.dofast.framework.operatelog.core.annotations.OperateLog;
import com.dofast.module.trade.controller.admin.order.vo.*;
import com.dofast.module.trade.convert.order.OrderCollectionConvert;
import com.dofast.module.trade.dal.dataobject.order.OrderCollectionDO;
import com.dofast.module.trade.dal.dataobject.order.OrderCollectionTypeDO;
import com.dofast.module.trade.dal.dataobject.order.TradeOrderDO;
import com.dofast.module.trade.service.order.OrderCollectionService;
import com.dofast.module.trade.service.order.OrderCollectionTypeService;
import com.dofast.module.trade.service.order.TradeOrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.dofast.framework.common.pojo.CommonResult.success;
import static com.dofast.framework.common.util.collection.CollectionUtils.convertList;
import static com.dofast.framework.common.util.collection.CollectionUtils.convertSet;
import static com.dofast.framework.operatelog.core.enums.OperateTypeEnum.EXPORT;

@Tag(name = "管理后台 - 采集任务")
@RestController
@RequestMapping("/trade/order-collection")
@Validated
public class OrderCollectionController {

    @Resource
    private OrderCollectionService orderCollectionService;
    @Resource
    private OrderCollectionTypeService orderCollectionTypeService;
    @Resource
    private TradeOrderService tradeOrderService;

    @PostMapping("/create")
    @Operation(summary = "创建采集任务")
    @PreAuthorize("@ss.hasPermission('trade:order-collection:create')")
    public CommonResult<Integer> createOrderCollection(@Valid @RequestBody OrderCollectionCreateReqVO createReqVO) {
        createReqVO.setStatus("created");
        return success(orderCollectionService.createOrderCollection(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新采集任务")
    @PreAuthorize("@ss.hasPermission('trade:order-collection:update')")
    public CommonResult<Boolean> updateOrderCollection(@Valid @RequestBody OrderCollectionUpdateReqVO updateReqVO) {
        orderCollectionService.updateOrderCollection(updateReqVO);
        return success(true);
    }

    @PostMapping("/update-status")
    @Operation(summary = "更新采集任务状态")
    @PreAuthorize("@ss.hasPermission('trade:order-collection:update')")
    public CommonResult<Boolean> updateOrderCollection(@Valid @RequestBody OrderCollectionUpdateStatusReqVO updateReqVO) {
        orderCollectionService.updateOrderCollectionStatus(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除采集任务")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('trade:order-collection:delete')")
    public CommonResult<Boolean> deleteOrderCollection(@RequestParam("id") Integer id) {
        orderCollectionService.deleteOrderCollection(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得采集任务")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('trade:order-collection:query')")
    public CommonResult<OrderCollectionRespVO> getOrderCollection(@RequestParam("id") Integer id) {
        OrderCollectionDO orderCollection = orderCollectionService.getOrderCollection(id);
        return success(OrderCollectionConvert.INSTANCE.convert(orderCollection));
    }

    @GetMapping("/list")
    @Operation(summary = "获得采集任务列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('trade:order-collection:query')")
    public CommonResult<List<OrderCollectionRespVO>> getOrderCollectionList(@RequestParam("ids") Collection<Integer> ids) {
        List<OrderCollectionDO> list = orderCollectionService.getOrderCollectionList(ids);
        return success(OrderCollectionConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/list-all")
    @Operation(summary = "获得采集任务列表")
    @PreAuthorize("@ss.hasPermission('trade:order-collection:query')")
    public CommonResult<List<OrderCollectionRespVO>> getOrderCollectionListByOrder(HttpServletRequest request) {
        Long orderId = null;
        List<Long> orderIds = null;
        String orderIdStr = request.getParameter("orderId");
        String orderIdsStr = request.getParameter("orderIds");
        if (orderIdStr == null) {
            if (orderIdsStr == null) {
                return success(new ArrayList<>());
            }
            String[] orderIdStrs = orderIdsStr.split(",");
            if (orderIdStrs.length == 0) {
                return success(new ArrayList<>());
            }
            orderIds = new ArrayList<>();
            for (int i = 0; i < orderIdStrs.length; i++) {
                orderIds.add(Long.valueOf(orderIdStrs[i]));
            }
        } else {
            orderId = Long.valueOf(orderIdStr);
        }

        OrderCollectionExportReqVO req= new OrderCollectionExportReqVO();
        req.setOrderId(orderId);
        req.setOrderIds(orderIds);
        List<OrderCollectionDO> list = orderCollectionService.getOrderCollectionList(req);
        if (CollUtil.isEmpty(list)){
            return success(new ArrayList<>());
        }

        List<OrderCollectionTypeDO> typeList = orderCollectionTypeService.getOrderCollectionTypeList(convertSet(list, OrderCollectionDO::getTypeId));

        List<TradeOrderDO> orderList = tradeOrderService.getOrderList(convertSet(list, OrderCollectionDO::getOrderId));



        return success(OrderCollectionConvert.INSTANCE.convertList(list, typeList, orderList, true));
    }
    @GetMapping("/list-all-by-mixin-order-id")
    @Operation(summary = "获得采集任务列表")
    @Parameter(name = "mixinOrderId", description = "销售订单编号", required = true, example = "2048")
    @PreAuthorize("@ss.hasPermission('trade:order-collection:query')")
    public CommonResult<List<OrderCollectionRespVO>> getOrderCollectionListByMixinOrder(@RequestParam("mixinOrderId") Long mixinOrderId) {
        TradeOrderListReqVO reqVO = new TradeOrderListReqVO();
        reqVO.setMixinOrderId(mixinOrderId);
        List<TradeOrderDO> orders = tradeOrderService.getOrderList(reqVO);

        OrderCollectionExportReqVO req= new OrderCollectionExportReqVO();
        req.setOrderIds(convertList(orders, TradeOrderDO::getId));
        List<OrderCollectionDO> list = orderCollectionService.getOrderCollectionList(req);
        if (CollUtil.isEmpty(list)){
            return success(new ArrayList<>());
        }

        List<OrderCollectionTypeDO> typeList = orderCollectionTypeService.getOrderCollectionTypeList(convertSet(list, OrderCollectionDO::getTypeId));

        List<TradeOrderDO> orderList = tradeOrderService.getOrderList(convertSet(list, OrderCollectionDO::getOrderId));

        return success(OrderCollectionConvert.INSTANCE.convertList(list, typeList, orderList, true));
    }

    @GetMapping("/page")
    @Operation(summary = "获得采集任务分页")
    @PreAuthorize("@ss.hasPermission('trade:order-collection:query')")
    public CommonResult<PageResult<OrderCollectionRespVO>> getOrderCollectionPage(@Valid OrderCollectionPageReqVO pageVO) {
        PageResult<OrderCollectionDO> pageResult = orderCollectionService.getOrderCollectionPage(pageVO);
        if (CollUtil.isEmpty(pageResult.getList())) {
            return success(PageResult.empty());
        }

        List<OrderCollectionTypeDO> typeList = orderCollectionTypeService.getOrderCollectionTypeList(convertSet(pageResult.getList(), OrderCollectionDO::getTypeId));

        List<TradeOrderDO> orderList = tradeOrderService.getOrderList(convertSet(pageResult.getList(), OrderCollectionDO::getOrderId));

        return success(OrderCollectionConvert.INSTANCE.convertPage(pageResult, typeList, orderList));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出采集任务 Excel")
    @PreAuthorize("@ss.hasPermission('trade:order-collection:export')")
    @OperateLog(type = EXPORT)
    public void exportOrderCollectionExcel(@Valid OrderCollectionExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<OrderCollectionDO> list = orderCollectionService.getOrderCollectionList(exportReqVO);
        // 导出 Excel
        List<OrderCollectionExcelVO> datas = OrderCollectionConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "采集任务.xls", "数据", OrderCollectionExcelVO.class, datas);
    }

}
