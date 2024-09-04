package com.dofast.module.trade.controller.admin.order;

import cn.hutool.core.collection.CollUtil;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.dofast.framework.common.pojo.CommonResult;
import com.dofast.framework.common.pojo.PageResult;
 
import com.dofast.framework.common.util.servlet.ServletUtils;
import com.dofast.framework.excel.core.util.ExcelUtils;
import com.dofast.framework.operatelog.core.annotations.OperateLog;
import com.dofast.module.channel.api.ordergoods.OrderGoodsApi;
import com.dofast.module.channel.api.ordergoods.dto.OrderGoodsGetDTO;
import com.dofast.module.channel.api.remark.RemarkApi;
import com.dofast.module.channel.api.remark.dto.RemarkDto;
import com.dofast.module.member.api.user.MemberUserApi;
import com.dofast.module.member.api.user.dto.MemberUserRespDTO;
import com.dofast.module.product.api.property.ProductPropertyValueApi;
import com.dofast.module.product.api.property.dto.ProductPropertyValueDetailRespDTO;
import com.dofast.module.product.api.spu.ProductSpuApi;
import com.dofast.module.product.api.spu.dto.ProductSpuRespDTO;
import com.dofast.module.trade.controller.admin.order.vo.*;
import com.dofast.module.trade.convert.order.OrderCollectionTypeConvert;
import com.dofast.module.trade.convert.order.TradeOrderConvert;
import com.dofast.module.trade.dal.dataobject.mixin.MixinOrderDO;
import com.dofast.module.trade.dal.dataobject.order.OrderCollectionTypeDO;
import com.dofast.module.trade.dal.dataobject.order.TradeOrderDO;
import com.dofast.module.trade.dal.dataobject.order.TradeOrderItemDO;

import com.dofast.module.trade.service.mixin.MixinOrderService;
import com.dofast.module.trade.service.order.TradeOrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import liquibase.pro.packaged.B;
import liquibase.pro.packaged.S;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import com.dofast.module.trade.dal.dataobject.order.TradeOrderLogDO;
import com.dofast.module.trade.service.order.TradeOrderLogService;
import com.dofast.module.trade.service.order.TradeOrderQueryService;
import com.dofast.module.trade.service.order.TradeOrderUpdateService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;



import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


 


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.*;

import static com.dofast.framework.common.pojo.CommonResult.success;
import static com.dofast.framework.common.util.collection.CollectionUtils.convertSet;
import static com.dofast.framework.operatelog.core.enums.OperateTypeEnum.EXPORT;
import static com.dofast.framework.security.core.util.SecurityFrameworkUtils.getLoginUserId;
import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.dofast.framework.common.pojo.CommonResult.success;
import static com.dofast.framework.common.util.collection.CollectionUtils.convertList;
import static com.dofast.framework.common.util.collection.CollectionUtils.convertSet;


@Tag(name = "管理后台 - 交易订单")
@RestController
@RequestMapping("/trade/order")
@Validated
@Slf4j
public class TradeOrderController {

 
    @Autowired
    private TradeOrderService tradeOrderService;

    @Autowired
    private ProductPropertyValueApi productPropertyValueApi;
    @Autowired
    private OrderGoodsApi orderGoodsApi;
    @Autowired
    private MemberUserApi memberUserApi;

    @Resource
    private TradeOrderUpdateService tradeOrderUpdateService;
    @Resource
    private TradeOrderQueryService tradeOrderQueryService;
    @Resource
    private TradeOrderLogService tradeOrderLogService;

    @Resource
    private RemarkApi remarkApi;

    @Resource
    private MixinOrderService mixinOrderService;


    @PostMapping("/page")
    @Operation(summary = "获得交易订单分页")
    @PreAuthorize("@ss.hasPermission('trade:order:query')")
    public CommonResult<PageResult<TradeOrderPageItemRespVO>> getOrderPage(@RequestBody TradeOrderPageReqVO reqVO) {
        // 查询订单
        PageResult<TradeOrderDO> pageResult = tradeOrderQueryService.getOrderPage(reqVO);
        if (CollUtil.isEmpty(pageResult.getList())) {
            return success(PageResult.empty());
        }

        // 查询用户信息
        Set<Long> userIds = CollUtil.unionDistinct(convertList(pageResult.getList(), TradeOrderDO::getUserId),
                convertList(pageResult.getList(), TradeOrderDO::getBrokerageUserId, Objects::nonNull));
        Map<Long, MemberUserRespDTO> userMap = memberUserApi.getUserMap(userIds);
        // 查询订单项
        List<TradeOrderItemDO> orderItems = tradeOrderQueryService.getOrderItemListByOrderId(
                convertSet(pageResult.getList(), TradeOrderDO::getId));
        // 最终组合
        return success(TradeOrderConvert.INSTANCE.convertPage(pageResult, orderItems, userMap));
    }


    @GetMapping("/summary")
    @Operation(summary = "获得交易订单统计")
    @PreAuthorize("@ss.hasPermission('trade:order:query')")
    public CommonResult<TradeOrderSummaryRespVO> getOrderSummary(TradeOrderPageReqVO reqVO) {
        return success(tradeOrderQueryService.getOrderSummary(reqVO));

    }
    @Resource
    ProductSpuApi productSpuApi;

    @GetMapping("/get-detail")
    @Operation(summary = "获得交易订单详情")
    @Parameter(name = "id", description = "订单编号", required = true, example = "1")
    @PreAuthorize("@ss.hasPermission('trade:order:query')")
    public CommonResult<TradeOrderDetailRespVO> getOrderDetail(@RequestParam("id") Long id) {
        // 查询订单
        TradeOrderDO order = tradeOrderQueryService.getOrder(id);
        if (order == null) {
            return success(null);
        }
        // 查询订单项
        List<TradeOrderItemDO> orderItems = tradeOrderQueryService.getOrderItemListByOrderId(id);

        // 拼接数据
        MemberUserRespDTO user = memberUserApi.getUser(order.getUserId());
        MemberUserRespDTO brokerageUser = order.getBrokerageUserId() != null ?
                memberUserApi.getUser(order.getBrokerageUserId()) : null;
        List<TradeOrderLogDO> orderLogs = tradeOrderLogService.getOrderLogListByOrderId(id);
        TradeOrderDetailRespVO orderDetailRespVO = TradeOrderConvert.INSTANCE.convert(order, orderItems, orderLogs, user, brokerageUser);
        for (TradeOrderDetailRespVO.Item item : orderDetailRespVO.getItems()) {
            if (item.getSpuId()>0){
                item.setSpuName(productSpuApi.getSpu(item.getSpuId()).getName());
            }
        }
        return success(orderDetailRespVO);
    }



    @GetMapping("/count-wait-delivery")
    @Operation(summary = "待发货订单总数")
    @PreAuthorize("@ss.hasPermission('trade:order:query')")
    public CommonResult<Integer> CountWaitDelivery() {
        List<TradeOrderDO> list = tradeOrderService.getWaitDelivery(10);
        Integer result = list == null? 0:list.size();
        return success(result);
    }

    @PostMapping("/create")
    @Operation(summary = "创建订单")
    @PreAuthorize("@ss.hasPermission('trade:order:create')")
    public CommonResult<Long> createOrder(@RequestBody TradeOrderCreateReqVO createReqVO,
                                          HttpServletRequest servletRequest) {
        // 获取登录用户、用户 IP 地址
        Long loginUserId = createReqVO.getUserId();
        String clientIp = ServletUtils.getClientIP(servletRequest);
        // 创建交易订单，预支付记录
        Long orderId = tradeOrderService.createOrder(loginUserId, clientIp, createReqVO);
//        tradeOrderService.offlinePay(orderId);
        return success(orderId);
    }

    @PutMapping("/close_order")
    @Operation(summary = "关闭订单")
    @Parameter(name = "id", description = "订单编号", required = true, example = "1")
    @PreAuthorize("@ss.hasPermission('trade:order:update')")
    public CommonResult<Boolean> closeOrder(@RequestParam("id") Long id) {
        // 创建交易订单，预支付记录
        Boolean flag = tradeOrderService.closeOrder(id);
        return success(flag);
    }

    @PostMapping("/set-address")
    @Operation(summary = "修改收货地址")
    @PreAuthorize("@ss.hasPermission('trade:order:update:address')")
    public CommonResult setAddress(@RequestBody TradeOrderReceiverAddress address, @RequestParam("id") Long id)  {
        tradeOrderService.updateOrderAddress(id, address);
        return success();
    }

    @GetMapping("/update-adjust-price")
    @Operation(summary = "更新调整价格")
    @PreAuthorize("@ss.hasPermission('trade:order:update:adjust')")
    public CommonResult setUpdateAdjust(@Valid @RequestParam("id") Long id, @Valid @RequestParam("adjustPrice") double adjustPrice) {
        adjustPrice = (adjustPrice * 100);
        Integer price = (Integer)(int) adjustPrice;
        tradeOrderService.updateAdjustPrice(id, price);
        return success();
    }

    @GetMapping("/offline-pay")
    @Operation(summary = "线下付款")
    @PreAuthorize("@ss.hasPermission('trade:order:update')")
    public CommonResult offlinePay(@Valid @RequestParam("id") Long id,Long bandCard) {
        tradeOrderService.offlinePay(id,bandCard);
        return success();
    }
    @PutMapping("/confirm_receipt")
    @Operation(summary = "确认收货")
    @Parameter(name = "id", description = "订单编号", required = true, example = "1")
    @PreAuthorize("@ss.hasPermission('trade:order:update')")
    public CommonResult<Boolean> confirmReceipt(@RequestParam("id") Long id) {
        // 创建交易订单，预支付记录
        Boolean flag = tradeOrderService.confirmReceipt(id);
        return success(flag);
    }

    @PutMapping("/update-system-user")
    @Operation(summary = "设置销售员")
    @Parameter(name = "id", description = "订单编号", required = true, example = "1")
    @Parameter(name = "systemUserId", description = "销售员", required = true, example = "1")
    @Parameter(name = "systemUserName", description = "销售员名称", required = false, example = "")
    @PreAuthorize("@ss.hasPermission('trade:order:update')")
    public CommonResult uppdateSystemUser(@Valid @RequestParam("id") Long id, @Valid @RequestParam("systemUserId") Long systemUserId, @RequestParam("systemUserName") String systemUserName) {
        systemUserName = systemUserName==null?"":systemUserName;
        tradeOrderService.updateSystemUser(id, systemUserId, systemUserName);
        return success();
    }

    @GetMapping("/list-all")
    @Operation(summary = "获取订单列表")
    @Parameter(name="mixinOrderId", description = "渠道订单编码", required=true, example = "1")
    @PreAuthorize("@ss.hasPermission('trade:order:query')")
    public CommonResult<List<TradeOrderPageItemRespVO>> getTradeOrderAllList(@Valid @RequestParam("mixinOrderId") Long mixinOrderId) {
        // 查询订单
        TradeOrderListReqVO reqVO = new TradeOrderListReqVO();
        reqVO.setMixinOrderId(mixinOrderId);
        List<TradeOrderDO> list = tradeOrderService.getOrderList(reqVO);
        if (CollUtil.isEmpty(list)) {
            return success(new ArrayList<>());
        }
        // 查询订单项
        List<TradeOrderItemDO> orderItems = tradeOrderService.getOrderItemListByOrderId(
                convertSet(list, TradeOrderDO::getId));
        // 查询商品属性
        List<ProductPropertyValueDetailRespDTO> propertyValueDetails = productPropertyValueApi
                .getPropertyValueDetailList(TradeOrderConvert.INSTANCE.convertPropertyValueIds(orderItems));
        // 最终组合
        return success(TradeOrderConvert.INSTANCE.convertList(list, orderItems, propertyValueDetails));
    }

    @GetMapping("/count-all")
    @Operation(summary = "获取商城订单总数")
    @PreAuthorize("@ss.hasPermission('trade:order:query')")
    public CommonResult<Integer> CountAll() {
        TradeOrderListReqVO reqVO = new TradeOrderListReqVO();
        List<TradeOrderDO> list = tradeOrderService.getOrderList(reqVO);
        Integer result = list == null? 0:list.size();
        return success(result);
    }

    @GetMapping("/count-month-money-thisYear")
    @Operation(summary = "获取商城订单今年成交额")
    @PreAuthorize("@ss.hasPermission('trade:order:query')")
    public CommonResult<Map<String, Integer>> CountMonthMoneyThisYear() {
        return success(tradeOrderService.getCountMonthMoneyThisYear());
    }

    @GetMapping("/count-month-money-lastYear")
    @Operation(summary = "获取商城订单去年成交额")
    @PreAuthorize("@ss.hasPermission('trade:order:query')")
    public CommonResult<Map<String, Integer>> CountMonthMoneyLastYear() {
        TradeOrderListReqVO reqVO = new TradeOrderListReqVO();
        List<TradeOrderDO> list = tradeOrderService.getOrderList(reqVO);
        Integer result = list == null? 0:list.size();
        return success(tradeOrderService.getCountMonthMoneyLastYear());
    }

    @GetMapping("/count-all-day")
    @Operation(summary = "当日订单数量")
    @PreAuthorize("@ss.hasPermission('trade:order:query')")
    public CommonResult<Long> CountByDay(){
        String format = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        return success(tradeOrderService.getCountByDay(format));
    }
        /*TradeOrderDO order = tradeOrderQueryService.getOrder(id);
        if (order == null) {
            return success(null);
        }
        // 查询订单项
        List<TradeOrderItemDO> orderItems = tradeOrderQueryService.getOrderItemListByOrderId(id);

        // 拼接数据
        MemberUserRespDTO user = memberUserApi.getUser(order.getUserId());
        MemberUserRespDTO brokerageUser = order.getBrokerageUserId() != null ?
                memberUserApi.getUser(order.getBrokerageUserId()) : null;
        List<TradeOrderLogDO> orderLogs = tradeOrderLogService.getOrderLogListByOrderId(id);
        return success(TradeOrderConvert.INSTANCE.convert(order, orderItems, orderLogs, user, brokerageUser));
    }*/

    @GetMapping("/get-express-track-list")
    @Operation(summary = "获得交易订单的物流轨迹")
    @Parameter(name = "id", description = "交易订单编号")
    @PreAuthorize("@ss.hasPermission('trade:order:query')")
    public CommonResult<List<?>> getOrderExpressTrackList(@RequestParam("id") Long id) {
        return success(TradeOrderConvert.INSTANCE.convertList02(
                tradeOrderQueryService.getExpressTrackList(id)));
    }

    @PutMapping("/delivery")
    @Operation(summary = "订单发货")
    @PreAuthorize("@ss.hasPermission('trade:order:update')")
    public CommonResult<Boolean> deliveryOrder(@RequestBody TradeOrderDeliveryReqVO deliveryReqVO) {
        tradeOrderUpdateService.deliveryOrder(deliveryReqVO);
        return success(true);
    }

    @PutMapping("/update-remark")
    @Operation(summary = "订单备注")
    @PreAuthorize("@ss.hasPermission('trade:order:update')")
    public CommonResult<Boolean> updateOrderRemark(@RequestBody TradeOrderRemarkReqVO reqVO) {
        tradeOrderUpdateService.updateOrderRemark(reqVO);
        return success(true);
    }

    @PutMapping("/update-price")
    @Operation(summary = "订单调价")
    @PreAuthorize("@ss.hasPermission('trade:order:update')")
    public CommonResult<Boolean> updateOrderPrice(@RequestBody TradeOrderUpdatePriceReqVO reqVO) {
        tradeOrderUpdateService.updateOrderPrice(reqVO);
        return success(true);
    }

    @PutMapping("/update-address")
    @Operation(summary = "修改订单收货地址")
    @PreAuthorize("@ss.hasPermission('trade:order:update')")
    public CommonResult<Boolean> updateOrderAddress(@RequestBody TradeOrderUpdateAddressReqVO reqVO) {
        tradeOrderUpdateService.updateOrderAddress(reqVO);
        return success(true);
    }

    @PutMapping("/set-deliver-type")
    @Operation(summary = "修改订单发货方式")
    @PreAuthorize("@ss.hasPermission('trade:order:update')")
    public CommonResult<Boolean> updateDeliveryType(@RequestBody TradeOrderUpdateDeliveryTypeReqVO reqVO){
        return success(tradeOrderService.updateOrderDeliveryType(reqVO));
    }
    @PutMapping("/pick-up-by-id")
    @Operation(summary = "订单核销")
    @Parameter(name = "id", description = "交易订单编号")
    @PreAuthorize("@ss.hasPermission('trade:order:pick-up')")
    public CommonResult<Boolean> pickUpOrderById(@RequestParam("id") Long id) {
        tradeOrderUpdateService.pickUpOrderByAdmin(id);
        return success(true);
    }

    @PutMapping("/pick-up-by-verify-code")
    @Operation(summary = "订单核销")
    @Parameter(name = "pickUpVerifyCode", description = "自提核销码")
    @PreAuthorize("@ss.hasPermission('trade:order:pick-up')")
    public CommonResult<Boolean> pickUpOrderByVerifyCode(@RequestParam("pickUpVerifyCode") String pickUpVerifyCode) {
        tradeOrderUpdateService.pickUpOrderByAdmin(pickUpVerifyCode);
        return success(true);
    }

    @GetMapping("/get-by-pick-up-verify-code")
    @Operation(summary = "查询核销码对应的订单")
    @Parameter(name = "pickUpVerifyCode", description = "自提核销码")
    @PreAuthorize("@ss.hasPermission('trade:order:query')")
    public CommonResult<TradeOrderDetailRespVO> getByPickUpVerifyCode(@RequestParam("pickUpVerifyCode") String pickUpVerifyCode) {
        TradeOrderDO tradeOrder = tradeOrderUpdateService.getByPickUpVerifyCode(pickUpVerifyCode);
        return success(TradeOrderConvert.INSTANCE.convert2(tradeOrder, null));
    }


    @GetMapping("/export-excel")
    @Operation(summary = "导出交易订单 Excel")
    @PreAuthorize("@ss.hasPermission('trade:order:export')")
    @OperateLog(type = EXPORT)
    public void exportOrderExcel(@Valid  @RequestParam(value = "deliveryTime",required = false) String[] deliveryTime,
                                 HttpServletResponse response) throws IOException {


        TradeOrderExportReqVO tradeOrderExportReqVO = new TradeOrderExportReqVO();
        if (deliveryTime!=null && ! "".equals(deliveryTime)  && deliveryTime.length>0 ){
            LocalDateTime[] localDateTimes=new LocalDateTime[2];
            for (int i = 0; i < deliveryTime.length; i++) {
                DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                String s = deliveryTime[i];
                LocalDateTime date2 = LocalDateTime.parse(deliveryTime[i], fmt);
                localDateTimes[i]=date2;
            }
            tradeOrderExportReqVO.setDeliveryTime(localDateTimes);
        }
        List<TradeOrderDO> list = tradeOrderService.getOrderList(tradeOrderExportReqVO);
        // 导出 Excel
        List<TradeOrderExcelVO> datas = TradeOrderConvert.INSTANCE.convertList05(list);
        datas.stream().forEach(v->{
            RemarkDto remarkByOrderId = remarkApi.getRemarkByOrderId(v.getId());
            if (remarkByOrderId!=null) {
                v.setSaleremark(remarkByOrderId.getRemark());
            }
            MixinOrderDO mixinOrder = mixinOrderService.getMixinOrder(v.getMixinOrderId());
            if (mixinOrder!=null) {
                v.setSaleNo(mixinOrder.getSaleNo());
            }
        });
        ExcelUtils.write(response, "交易订单.xls", "数据", TradeOrderExcelVO.class, datas);
    }



}
