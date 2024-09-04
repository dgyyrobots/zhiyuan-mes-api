package com.dofast.module.channel.controller.admin.order;

import com.dofast.framework.common.exception.ErrorCode;
import com.dofast.framework.common.pojo.CommonResult;
import com.dofast.framework.common.pojo.PageResult;
import com.dofast.framework.excel.core.util.ExcelUtils;
import com.dofast.framework.operatelog.core.annotations.OperateLog;
import com.dofast.module.channel.biz.Dian3;
import com.dofast.module.channel.controller.admin.order.vo.*;
import com.dofast.module.channel.convert.order.OrderConvert;
import com.dofast.module.channel.dal.dataobject.order.OrderDO;
import com.dofast.module.channel.serv.OrderServ;
import com.dofast.module.channel.service.order.ChannelOrderService;
import com.dofast.module.channel.service.ordergoods.OrderGoodsService;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.security.PermitAll;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.dofast.framework.common.pojo.CommonResult.error;
import static com.dofast.framework.common.pojo.CommonResult.success;
import static com.dofast.framework.operatelog.core.enums.OperateTypeEnum.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "管理后台 - 主订单")
@RestController
@RequestMapping("/channel/order")
@Validated
public class OrderController {

    @Autowired
    private ChannelOrderService channelOrderService;

    @Autowired
    private OrderGoodsService orderGoodsService;

    @Autowired
    private OrderServ orderServ;

    @PostMapping("/create")
    @Operation(summary = "创建主订单")
    @PreAuthorize("@ss.hasPermission('channel:order:create')")
    public CommonResult<Integer> createOrder(@Valid @RequestBody OrderCreateReqVO createReqVO) {
        return success(channelOrderService.createOrder(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新主订单")
    @PreAuthorize("@ss.hasPermission('channel:order:update')")
    public CommonResult<Boolean> updateOrder(@Valid @RequestBody OrderUpdateReqVO updateReqVO) {
        channelOrderService.updateOrder(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除主订单")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('channel:order:delete')")
    public CommonResult<Boolean> deleteOrder(@RequestParam("id") Integer id) {
        channelOrderService.deleteOrder(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得主订单")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('channel:order:query')")
    public CommonResult<OrderRespVO> getOrder(@RequestParam("id") Integer id) {
        OrderDO order = channelOrderService.getOrder(id);
        return success(OrderConvert.INSTANCE.convert(order));
    }

    @GetMapping("/list")
    @Operation(summary = "获得主订单列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('channel:order:query')")
    public CommonResult<List<OrderRespVO>> getOrderList(@RequestParam("ids") Collection<Integer> ids) {
        List<OrderDO> list = channelOrderService.getOrderList(ids);
        return success(OrderConvert.INSTANCE.convertList(list));
    }

    @PostMapping("/page")
    @Operation(summary = "获得主订单分页")
    @PreAuthorize("@ss.hasPermission('channel:order:query')")
    public CommonResult<PageResult<OrderDOGoodsVO>> getOrderPage(@Valid @RequestBody  OrderListQueryVO orderListQueryVO) {
        PageResult<OrderDOGoodsVO> pageResult = channelOrderService.getOrderPage(orderListQueryVO);
        return success(pageResult);
    }

    @PostMapping("/to-inner-shop")
    @Operation(summary = "渠道订单转商城订单")
    @PreAuthorize("@ss.hasPermission('channel:order:query')")
    public CommonResult<Long> getToInnerShop(@Valid @RequestBody OrderToInnerShopVO data) {
        Long tradeId = channelOrderService.ChannelOrderToMallOrder(data);
        return success(tradeId);
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出主订单 Excel")
    @PreAuthorize("@ss.hasPermission('channel:order:export')")
    @OperateLog(type = EXPORT)
    public void exportOrderExcel(@Valid OrderExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<OrderDO> list = channelOrderService.getOrderList(exportReqVO);
        // 导出 Excel
        List<OrderExcelVO> datas = OrderConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "主订单.xls", "数据", OrderExcelVO.class, datas);
    }

    @GetMapping("/total")
    @Operation(summary = "订单统计")
    @PreAuthorize("@ss.hasPermission('channel:order:query')")
    public CommonResult<OrdedrNumberVO> getOrderNumbers() {
        OrdedrNumberVO ordedrNumberVO = channelOrderService.getOrderNumber();
        return success(OrderConvert.INSTANCE.covert(ordedrNumberVO));
    }

    @GetMapping("/shop-total")
    @Operation(summary = "获得选择的店铺订单统计信息")
    @Parameter(name = "shopId", description = "店铺编码", required = false, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('channel:order:query')")
    public CommonResult<OrdedrNumberVO> getShopOrderNumbers(@RequestParam(name = "shopId", defaultValue = "0") String shopId) {
        OrdedrNumberVO ordedrNumberVO = channelOrderService.getShopOrderNumber(shopId);
        return success(OrderConvert.INSTANCE.covert(ordedrNumberVO));
    }

    @GetMapping("/shop-day-total")
    @Operation(summary = "获得选择的店铺日订单统计信息")
    @Parameter(name = "shopId", description = "店铺编码", required = false, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('channel:order:query')")
    public CommonResult<List<OrderShopDayNVO>> getShopDayOrderNumbers(@RequestParam(name = "shopId", defaultValue = "0") String shopId) {
        List<OrderShopDayNVO> orderShopDayNVOS = channelOrderService.getShopDayOrderNumber(shopId);
        return success(OrderConvert.INSTANCE.convertList03(orderShopDayNVOS));
    }

    @GetMapping("/day-total-money")
    @Operation(summary = "获得日订单所有的金额数量")
    @PreAuthorize("@ss.hasPermission('channel:order:query')")
    public CommonResult<BigDecimal> CountShopDayOrderMoney() {
        BigDecimal result = channelOrderService.getDayOrderMoney();
        return success(result);
    }

    @GetMapping("/year-total-by-refType")
    @Operation(summary = "获得订单今年的数量，按种类划分")
    @PreAuthorize("@ss.hasPermission('channel:order:query')")
    public CommonResult<Map<String, Integer>> CountShopYearOrderRefType() {
        return success(channelOrderService.getCountShopYearOrderRefType());
    }

    @GetMapping("/month-total-of-money")
    @Operation(summary = "获得订单今年每月的成交额")
    @PreAuthorize("@ss.hasPermission('channel:order:query')")
    public CommonResult<Map<String, Integer>> CountShopMonthOrderMoney() {
        return success(channelOrderService.getCountShopMonthOrderMoney());
    }

    @Autowired
    Dian3 dian3;


    @RequestMapping("/get")
    @Operation(summary = "获取订单")
    @PermitAll
    @OperateLog(type = CHANNEL_ORDER_GET)
    public CommonResult getOrders(@RequestParam(value="shopCode", defaultValue = "") String shopCode) {
        HashMap params = new HashMap();
        if (shopCode.equals(""))params.put("posCode", shopCode);
        String ret = dian3.init("ds.omni.erp.third.order.query", params)
                .buildSign()
                .doPost();
        return success();
    }

    @PostMapping("/shop-order-shipment")
    @Operation(summary = "店铺订单发货")
    @PreAuthorize("@ss.hasPermission('channel:order:query')")
    public CommonResult<Boolean> shopOrderShipment(@Valid @RequestBody OrderShipmentReq orderShipmentReq) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("refOid", orderShipmentReq.getRefOid());
        params.put("posCode", orderShipmentReq.getPosCode());
        params.put("feature", orderShipmentReq.getFeature());
        if (orderShipmentReq.getPackages() == null){
            return error(new ErrorCode(3049003, "物流包裹信息不能为空"));
        }
        params.put("packages", orderShipmentReq.getPackages());
        orderServ.orderShopShipment(params);
        return success(true);
    }

    @PostMapping("/modify-order-note")
    @Operation(summary = "店铺订单修改备注与旗帜")
    @PreAuthorize("@ss.hasPermission('channel:order:query')")
    public CommonResult<Boolean> modifyOrderNote(@Valid @RequestBody ModifyOrderNoteReq modifyOrderNoteReq) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("refOid", modifyOrderNoteReq.getRefOid());
        params.put("posCode", modifyOrderNoteReq.getPosCode());
        params.put("memo", modifyOrderNoteReq.getMemo());
        params.put("flag", modifyOrderNoteReq.getFlag());
        params.put("operator", modifyOrderNoteReq.getOperator());
        orderServ.modifyOrderNote(params);
        return success(true);
    }

    @PostMapping("/shop-order-shipped")
    @Operation(summary = "店铺订单出库（京东厂送专用)")
    @PreAuthorize("@ss.hasPermission('channel:order:query')")
    public CommonResult<Boolean> shopOrderShipped(@Valid @RequestBody ShopOrderShippedReq shopOrderShippedReq) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("refOid", shopOrderShippedReq.getRefOid());
        params.put("posCode", shopOrderShippedReq.getPosCode());
        orderServ.shopOrderShipped(params);
        return success(true);
    }

    @PostMapping("/virtual-delivery")
    @Operation(summary = "商城原始订单虚拟发货")
    @PreAuthorize("@ss.hasPermission('channel:order:query')")
    public CommonResult<Boolean> virtualDelivery(@Valid @RequestBody VirtualDeliveryReq virtualDeliveryReq) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("refOid", virtualDeliveryReq.getRefOid());
        params.put("posCode", virtualDeliveryReq.getPosCode());
        orderServ.virtualDelivery(params);
        return success(true);
    }

    @PostMapping("/after-sale-inquiry")
    @Operation(summary = "店铺售后订单查询")
    @PreAuthorize("@ss.hasPermission('channel:order:query')")
    public CommonResult<Boolean> afterSaleInquiry(@Valid @RequestBody ShopAfterSaleReq shopAfterSaleReq) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("pageNo", shopAfterSaleReq.getPageNo());
        params.put("pageSize", shopAfterSaleReq.getPageSize());
        params.put("startTime", shopAfterSaleReq.getStartTime());
        params.put("endTime", shopAfterSaleReq.getEndTime());
        params.put("timeType", shopAfterSaleReq.getTimeType());
        params.put("refOid", shopAfterSaleReq.getRefOid());
        params.put("refAid", shopAfterSaleReq.getRefAid());
        orderServ.getShopAfterSale(params);
        return success(true);
    }

    @PostMapping("/after-sale-push")
    @Operation(summary = "店铺售后订单推送")
    @PreAuthorize("@ss.hasPermission('channel:order:query')")
    public CommonResult<Boolean> afterSalePush(@Valid @RequestBody ShopAfterSalePushReq shopAfterSalePushReq) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("content", shopAfterSalePushReq);
        orderServ.shopAfterSalePush(params);
        return success(true);
    }


}
