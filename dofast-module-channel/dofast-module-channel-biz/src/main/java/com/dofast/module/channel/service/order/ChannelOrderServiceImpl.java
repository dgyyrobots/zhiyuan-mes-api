package com.dofast.module.channel.service.order;

import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.JSON;
import com.dofast.framework.common.enums.TerminalEnum;
import com.dofast.framework.common.pojo.PageResult;
import com.dofast.module.channel.controller.admin.order.vo.*;
import com.dofast.module.channel.controller.admin.shop.vo.ShopExportReqVO;
import com.dofast.module.channel.convert.order.OrderConvert;
import com.dofast.module.channel.dal.dataobject.order.OrderDO;
import com.dofast.module.channel.dal.dataobject.ordergoods.OrderGoodsDO;
import com.dofast.module.channel.dal.dataobject.shop.ShopDO;
import com.dofast.module.channel.dal.mysql.order.ChannelOrderMapper;

import java.math.BigDecimal;
import java.time.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import javax.annotation.Resource;
import javax.validation.Valid;

import com.dofast.module.channel.dal.mysql.ordergoods.OrderGoodsMapper;
import com.dofast.module.channel.dal.mysql.shop.ShopMapper;
import com.dofast.module.member.controller.admin.user.vo.MemberUserCreateReqVO;
import com.dofast.module.member.dal.dataobject.user.MemberUserDO;
import com.dofast.module.member.dal.mysql.user.MemberUserMapper;
import com.dofast.module.member.service.user.MemberUserService;
import com.dofast.module.trade.dal.dataobject.order.TradeOrderDO;
import com.dofast.module.trade.dal.dataobject.order.TradeOrderItemDO;
import com.dofast.module.trade.dal.mysql.order.TradeOrderItemMapper;
import com.dofast.module.trade.dal.mysql.order.TradeOrderMapper;
import com.dofast.module.trade.enums.TradeOrderDeliveryStatusEnum;
import com.dofast.module.trade.enums.order.TradeOrderAfterSaleStatusEnum;
import com.dofast.module.trade.enums.order.TradeOrderStatusEnum;
import jodd.util.StringUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.format.DateTimeFormatter;
import java.util.*;

import static com.dofast.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.dofast.module.channel.enums.ErrorCodeConstants.*;

/**
 * 主订单 Service 实现类
 *
 * @author 惠智造
 */
@Service("ChannelOrderService")
public class ChannelOrderServiceImpl implements ChannelOrderService {

    @Autowired
    private ChannelOrderMapper channelOrderMapper;

    @Autowired
    private TradeOrderMapper tradeOrderMapper;

    @Autowired
    private MemberUserMapper memberUserMapper;

    @Autowired
    private ShopMapper shopMapper;

    @Autowired
    private OrderGoodsMapper orderGoodsMapper;

    @Autowired
    private TradeOrderItemMapper tradeOrderItemMapper;

    @Autowired
    private MemberUserService memberUserService;

    @Override
    public Integer createOrder(OrderCreateReqVO createReqVO) {
        // 插入
        OrderDO order = OrderConvert.INSTANCE.convert(createReqVO);
        order.setProps(JSON.toJSONString(createReqVO.getProps()));
        channelOrderMapper.insert(order);
        // 返回
        return order.getId();
    }

    @Override
    public void updateOrder(OrderUpdateReqVO updateReqVO) {
        // 校验存在
        validateOrderExists(updateReqVO.getId());
        // 更新
        OrderDO updateObj = OrderConvert.INSTANCE.convert(updateReqVO);
        channelOrderMapper.updateById(updateObj);
    }

    @Override
    public void deleteOrder(Integer id) {
        // 校验存在
        validateOrderExists(id);
        // 删除
        channelOrderMapper.deleteById(id);
    }

    private void validateOrderExists(Integer id) {
        if (channelOrderMapper.selectById(id) == null) {
            throw exception(ORDER_NOT_EXISTS);
        }
    }

    @Override
    public OrderDO getOrder(Integer id) {
        return channelOrderMapper.selectById(id);
    }

    @Override
    public List<OrderDO> getOrderList(Collection<Integer> ids) {
        return channelOrderMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<OrderDOGoodsVO> getOrderPage(OrderListQueryVO orderListQueryVO) {
        PageResult<OrderDOGoodsVO> pageResult = new PageResult<>();
        List<OrderDOGoodsVO> list = new ArrayList<>();
        OrderPageReqVO orderPageReqVO = new OrderPageReqVO();
        orderPageReqVO.setRefOid(orderListQueryVO.getOrderNo());
        orderPageReqVO.setRefType(orderListQueryVO.getChannel());
        orderPageReqVO.setOpenBuyerNick(orderListQueryVO.getCustomKeyword());
        orderPageReqVO.setPosCode(orderListQueryVO.getShopCode());
        orderPageReqVO.setStatus(orderListQueryVO.getOrderStatus());
        orderPageReqVO.setPageNo(orderListQueryVO.getPageNo());
        orderPageReqVO.setPageSize(orderListQueryVO.getPageSize());
        PageResult<OrderDO> orderDOPageResult = channelOrderMapper.selectPageOrder(orderPageReqVO);
        List<OrderDO> orderDOList = orderDOPageResult.getList();
        for (OrderDO orderDO : orderDOList){
            if (orderDO != null){
                List<OrderGoodsDO> orderGoodsDOList = orderGoodsMapper.selectPageOrderGoods(
                        orderListQueryVO.getGoodsKeyword(), orderDO.getRefOid());
                OrderDOGoodsVO orderDOGoodsVO = new OrderDOGoodsVO();
                BeanUtils.copyProperties(orderDO, orderDOGoodsVO);
                if (orderGoodsDOList == null){
                    orderGoodsDOList = new ArrayList<>();
                }
                orderDOGoodsVO.setOrderGoodsDOList(orderGoodsDOList);
                list.add(orderDOGoodsVO);
            }
        }
        pageResult.setList(list);
        pageResult.setTotal(orderDOPageResult.getTotal());
        return pageResult;
    }



    @Override
    @Transactional
    public Long ChannelOrderToMallOrder(OrderToInnerShopVO data) {
        MemberUserDO memberUserDO;
        TradeOrderDO tradeOrderDO = new TradeOrderDO();

        OrderDO orderDO;
        Long createUserId=null;
        //判断传来的数据是否为null,null抛出异常，不是则赋值给商城订单
        if (data != null) {
            /*判断传来的用户ID是否为null，手机号是否为null，有则去数据表中查询，将数据赋值给商城订单，无则抛出异常，
             其中根据手机号查需要返回空值，如果不是空值则报错*/
            if (data.getUserId() == null) {
                if (data.getUserMobile() == null){
                    throw exception(USER_ID_AND_INFORMATION_NULL);
                }else {
                    memberUserDO = memberUserMapper.selectByMobile(data.getUserMobile());
                    if (memberUserDO == null)/*{
                        throw exception(USER_PHONE_EXIST);
                    }else */{
                        MemberUserCreateReqVO memberUserCreateReqVO = new MemberUserCreateReqVO();
                        memberUserCreateReqVO.setNickname(data.getUserNickname());
                        memberUserCreateReqVO.setMobile(data.getUserMobile());
                        createUserId = memberUserService.createUser(memberUserCreateReqVO);
                        tradeOrderDO.setUserId(createUserId);
                    }else {
                        tradeOrderDO.setUserId(memberUserDO.getId());
                        createUserId=memberUserDO.getId();
                    }
                }
            }else {
                memberUserDO = memberUserMapper.selectById(data.getUserId());
                if (memberUserDO != null){
                    tradeOrderDO.setUserId(memberUserDO.getId());
                    createUserId = memberUserDO.getId();
                    tradeOrderDO.setUserIp(memberUserDO.getLoginIp());
                }else {
                    MemberUserCreateReqVO memberUserCreateReqVO = new MemberUserCreateReqVO();
                    memberUserCreateReqVO.setNickname(data.getUserNickname());
                    memberUserCreateReqVO.setMobile(data.getUserMobile());
                    createUserId = memberUserService.createUser(memberUserCreateReqVO);
                    tradeOrderDO.setUserId(createUserId);
                }
            }
            if (data.getSystemUserId() != null){
                tradeOrderDO.setSystemUserId(data.getSystemUserId());
            }else {
                throw exception(SYSTEM_USER_ID_NOT_EXIST);
            }
            if (data.getSystemUserName() == null){
                throw exception(SYSTEM_USER_NAME_NOT_EXIST);
            }else {
                tradeOrderDO.setSystemUserName(data.getSystemUserName());
            }
            if (data.getReceiverAreaId() == null) {
                throw exception(RECEIVER_AREA_ID_NULL);
            }else {
                Integer receiverAreaId = data.getReceiverAreaId();
                tradeOrderDO.setReceiverAreaId(receiverAreaId);
            }
            if (data.getReceiverPostCode() != null && !"".equals(data.getReceiverPostCode())) {
                String receiverPostCode = data.getReceiverPostCode();
                tradeOrderDO.setReceiverPostCode(Integer.valueOf(receiverPostCode));
            }
            if (data.getReceiverDetailAddress() == null) {
                throw exception(RECEIVER_DETAIL_ADDRESS_NULL);
            }else {
                String receiverDetailAddress = data.getReceiverDetailAddress();
                tradeOrderDO.setReceiverDetailAddress(receiverDetailAddress);
            }
            if (data.getReceiverName() == null) {
                throw exception(RECEIVER_NAME_NULL);
            }else {
                String receiverName = data.getReceiverName();
                tradeOrderDO.setReceiverName(receiverName);
            }
            if (data.getReceiverMobile() == null) {
                throw exception(RECEIVER_MOBILE_NULL);
            }else {
                String receiverMobile = data.getReceiverMobile();
                tradeOrderDO.setReceiverMobile(receiverMobile);
            }
            if (data.getChannelOrderId() == null) {
                throw exception(ORDER_ID_NULL);
            }else {
                String channelOrderId = data.getChannelOrderId();
                orderDO = channelOrderMapper.selectById(channelOrderId);
                if (orderDO.getIsTranslate() == true){
                    throw exception(ORDER_HAS_TRANSLATED);
                }
                //复制渠道订单的内容到商城订单，因为属性名和表内列名的缘故无法做到一一对应所以不采用BeanUtils工具
                tradeOrderDO.setChannelOrderId(String.valueOf(orderDO.getId()));
                tradeOrderDO.setNo(IdUtil.getSnowflakeNextId() + "");
                tradeOrderDO.setChannelOrderNo(orderDO.getRefOid());
                tradeOrderDO.setType(30);
                tradeOrderDO.setChannel(orderDO.getRefType());
                //获取渠道店铺的信息
                ShopDO shopDO = shopMapper.selectByPosCode(orderDO.getPosCode());
                tradeOrderDO.setChannelShopId(Long.parseLong(shopDO.getId()));
                tradeOrderDO.setChannelShopName(shopDO.getDisplayName());
                tradeOrderDO.setTerminal(TerminalEnum.H5.getTerminal());
                tradeOrderDO.setRemark(orderDO.getBuyerMemo());
                //判断发货状态
                tradeOrderDO.setDeliveryStatus(Byte.valueOf(String.valueOf(getTradeOrderDeliveryStatus(orderDO.getStatus()))));
                //支付时间查询，如果大于0，则返回true，否则返回false
                if (orderDO.getPayTime().toEpochSecond(ZoneOffset.UTC) > 0) {
                    tradeOrderDO.setPayed(true);
                } else {
                    tradeOrderDO.setPayed(false);
//                    tradeOrderDO.setStatus(0);
                }
                switch (orderDO.getStatus()){
                    case "UNPAID":
                        tradeOrderDO.setStatus(0);
                        break;
                    case "NOT_SHIPPED":
                        tradeOrderDO.setStatus(10);
                        break;
                    case "SHIPPED":
                        tradeOrderDO.setStatus(20);
                        break;
                    case "COMPLETE":
                        tradeOrderDO.setStatus(30);
                        break;
                    case "CANCEL":
                        tradeOrderDO.setStatus(40);
                        break;

                }

                if ("NO_REFUND".equals(orderDO.getRefundStatus())){
                    tradeOrderDO.setRefundStatus(0);
                    tradeOrderDO.setAfterSaleStatus(0);
                }else {
                    tradeOrderDO.setRefundStatus(1);
                    tradeOrderDO.setAfterSaleStatus(1);
                }
                tradeOrderDO.setPayTime(orderDO.getPayTime());
                tradeOrderDO.setFinishTime(orderDO.getFinishTime());
                tradeOrderDO.setOrderPrice(orderDO.getTotalPrice().intValue()*100);
                tradeOrderDO.setDiscountPrice(orderDO.getDiscountFee().intValue()*100);
                tradeOrderDO.setDeliveryPrice(orderDO.getPostFee().intValue()*100);
                tradeOrderDO.setPayPrice(orderDO.getPayment().intValue()*100);
                tradeOrderDO.setLogisticsId("");
                tradeOrderDO.setLogisticsNo(orderDO.getLogisticsOrderNo());
//                tradeOrderDO.setDeliveryTime(orderDO.getShippingTime());
                tradeOrderDO.setCreator(orderDO.getCreator());
                tradeOrderDO.setCreateTime(orderDO.getCreateTime());
                tradeOrderDO.setUpdater(orderDO.getUpdater());
                tradeOrderDO.setUpdateTime(orderDO.getUpdateTime());
                tradeOrderDO.setDeleted(orderDO.getDeleted());
                tradeOrderDO.setUserRemark(orderDO.getOpenBuyerId()+orderDO.getOpenBuyerNick());
                /* NONE-未知,NO_REFUND-无退款,PART_REFUNDED-部分退款,ALL_REFUNDED-全部退款 */
                tradeOrderDO.setAfterSaleStatus(getTradeOrderAfterSaleStatus(orderDO.getRefundStatus()));
                tradeOrderDO.setOriginalPrice(0);
                tradeOrderDO.setAdjustPrice(0);
                tradeOrderDO.setRefundPrice(0);
                tradeOrderDO.setCouponPrice(0);
                tradeOrderDO.setPointPrice(0);
                //查询商品信息
                List<OrderGoodsDO> orderGoodsDOList = new ArrayList<>();
                orderGoodsDOList = orderGoodsMapper.selectListByRefOid(orderDO.getRefOid(),
                        orderDO.getTenantId());
                long sum = orderGoodsDOList.stream().mapToLong(OrderGoodsDO::getNum).sum();
                tradeOrderDO.setProductCount(Integer.valueOf(Long.valueOf(sum).intValue()));
                tradeOrderDO.setRemark(orderDO.getSellerMemo());
            }
            OrderDO orderDO1 = channelOrderMapper.selectById(data.getChannelOrderId());
            List<ShopDO> shopDOS = shopMapper.selectList(new ShopExportReqVO().setChannel(orderDO1.getRefType()));
            if (shopDOS.size()<=0){
                throw exception(TRADE_ORDER_INSERT_FAIL);
            }
            String bankCard = shopDOS.get(0).getBankCard();
            if (bankCard==null || StringUtil.isEmpty(bankCard)){
                throw exception(BANKCARD_IS_NOT_EXIES);
            }
            tradeOrderDO.setDepositor(Long.valueOf(bankCard));
            tradeOrderDO.setDeliveryType(3);
            tradeOrderMapper.insert(tradeOrderDO);
            Long tradeOrderId = tradeOrderMapper.getLastInsertId();
            //查询商品信息
            List<OrderGoodsDO> orderGoodsDOList = orderGoodsMapper.selectListByRefOid(orderDO.getRefOid(),
                    orderDO.getTenantId());
            //为TradeOrderItem赋值
            for (OrderGoodsDO orderGoodsDO : orderGoodsDOList) {
                TradeOrderItemDO tradeOrderItemDO = new TradeOrderItemDO();
                // 对每个OrderGoodsDO对象执行相应的操作
                tradeOrderItemDO.setChannelOrderItemId(String.valueOf(orderGoodsDO.getId()));
                tradeOrderItemDO.setUserId(createUserId);
                tradeOrderItemDO.setOrderId(tradeOrderId);
                tradeOrderItemDO.setChannelOrderId(String.valueOf(orderDO.getId()));
                tradeOrderItemDO.setSkuId(Long.valueOf(0));
                tradeOrderItemDO.setSpuName(orderGoodsDO.getTitle()+orderGoodsDO.getStandards());
                tradeOrderItemDO.setSpuId(Long.valueOf(0));
                tradeOrderItemDO.setPicUrl(orderGoodsDO.getPicUrl());
                tradeOrderItemDO.setCount(orderGoodsDO.getNum());
                tradeOrderItemDO.setOriginalPrice(orderGoodsDO.getTotalPrice().intValue()*100);
                tradeOrderItemDO.setOriginalUnitPrice(orderGoodsDO.getPrice().intValue()*100);
                /* NONE-未知,NO_REFUND-无退款,PART_REFUNDED-部分退款,ALL_REFUNDED-全部退款 */
                tradeOrderItemDO.setAfterSaleStatus(getTradeOrderAfterSaleStatus(orderDO.getRefundStatus()));
                tradeOrderItemDO.setCreator(orderGoodsDO.getCreator());
                tradeOrderItemDO.setCreateTime(orderGoodsDO.getCreateTime());
                tradeOrderItemDO.setUpdater(orderGoodsDO.getUpdater());
                tradeOrderItemDO.setUpdateTime(orderGoodsDO.getUpdateTime());
                tradeOrderItemDO.setDeleted(orderGoodsDO.getDeleted());
                tradeOrderItemDO.setTenantId(Long.valueOf(orderDO.getTenantId()));
                tradeOrderItemMapper.insert(tradeOrderItemDO);
            }
            if (tradeOrderId != null){
                orderDO.setIsTranslate(true);
                orderDO.setTradeOrderId(tradeOrderId);
                channelOrderMapper.updateById(orderDO);
                return Long.valueOf(tradeOrderId);
            }else {
                throw exception(TRADE_ORDER_INSERT_FAIL);
            }
        }else {
            throw exception(INFORMATION_NULL);
        }
    }

    @Override
    public List<OrderDO> getOrderList(OrderExportReqVO exportReqVO) {
        return channelOrderMapper.selectList(exportReqVO);
    }



    @Override
    public HashMap pushOrders(@Valid ArrayList<OrderReceiveVO> orderPushReqVOList) {
        HashMap retMap = new HashMap();
        ArrayList contentList = new ArrayList();
        orderPushReqVOList.forEach(reqVO -> {

        });
        retMap.put("content", contentList);

        return  retMap;
    }

    //获取全部订单的今日，昨日，全部
    @Override
    public OrdedrNumberVO getOrderNumber() {
        Map<String, Object> result = channelOrderMapper.selectAllOrderNVO("0");
        // 解析查询结果
        Long orderCount = result.get("order_count") == null? (Long) result.get("order_count"):0l;
        BigDecimal orderTotal = result.get("order_total") == null? (BigDecimal) result.get("order_total"):BigDecimal.ZERO;
        OrdedrNumberVO ordedrNumberVO =new OrdedrNumberVO();
        ordedrNumberVO.setOrderCount(orderCount.intValue());
        ordedrNumberVO.setOrderMoneyCount(orderTotal.intValue());
        LocalDate date = LocalDate.now();
        // 将日期转换为起始时间和结束时间
        LocalDateTime startDateTime = date.atStartOfDay();
        LocalDateTime endDateTime = date.plusDays(1).atStartOfDay();
        result = channelOrderMapper.selectOrderNVO("0", startDateTime, endDateTime);
        orderCount = result.get("order_count") == null? (Long) result.get("order_count"):0l;
        ordedrNumberVO.setTodayOrderCount(orderCount.intValue());
        date = LocalDate.now().minusDays(1);
        startDateTime = date.atStartOfDay();
        endDateTime = date.plusDays(1).atStartOfDay();
        result = channelOrderMapper.selectOrderNVO("0", startDateTime, endDateTime);
        orderCount = result.get("order_count") == null? (Long) result.get("order_count"):0l;
        ordedrNumberVO.setYestdayOrderCount(orderCount.intValue());
        return ordedrNumberVO;
    }

    //获取店铺的昨日，今日，全部
    @Override
    public OrdedrNumberVO getShopOrderNumber(String shopId) {
        Map<String, Object> result = channelOrderMapper.selectAllOrderNVO(shopId);
        // 解析查询结果
        Long orderCount = result.get("order_count") == null? (Long) result.get("order_count"):0l;;
        BigDecimal orderTotal = result.get("order_total") == null? (BigDecimal) result.get("order_total"):BigDecimal.ZERO;
        OrdedrNumberVO ordedrNumberVO =new OrdedrNumberVO();
        ordedrNumberVO.setOrderCount(orderCount.intValue());
        if (orderTotal == null){
            orderTotal = BigDecimal.ZERO;
        }
        ordedrNumberVO.setOrderMoneyCount(orderTotal.intValue());
        LocalDate date = LocalDate.now();
        // 将日期转换为起始时间和结束时间
        LocalDateTime startDateTime = date.atStartOfDay();
        LocalDateTime endDateTime = date.plusDays(1).atStartOfDay();
        result = channelOrderMapper.selectOrderNVO(shopId, startDateTime, endDateTime);
        orderCount = result.get("order_count") == null? (Long) result.get("order_count"):0l;
        ordedrNumberVO.setTodayOrderCount(orderCount.intValue());
        date = LocalDate.now().minusDays(1);
        startDateTime = date.atStartOfDay();
        endDateTime = date.plusDays(1).atStartOfDay();
        result = channelOrderMapper.selectOrderNVO(shopId, startDateTime, endDateTime);
        orderCount = result.get("order_count") == null? (Long) result.get("order_count"):0l;
        ordedrNumberVO.setYestdayOrderCount(orderCount.intValue());
        return ordedrNumberVO;
    }

    @Override
    public List<OrderShopDayNVO> getShopDayOrderNumber(String shopId) {
        LocalDate now = LocalDate.now();
        // 获取七天前的日期和时间
        LocalDate sevenDaysAgo = now.minusDays(6);
        // 定义日期格式化器，用于将日期格式化为字符串
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        // 将日期格式化为字符串
        String nowStr = formatter.format(now.plusDays(1));
        String sevenDaysAgoStr = formatter.format(sevenDaysAgo);
        List<Map<String, Object>> resultList = channelOrderMapper.selectOrderDay(shopId, sevenDaysAgoStr, nowStr);
        // 构建结果
        List<OrderShopDayNVO> result = new ArrayList<>();
        for (LocalDate date = sevenDaysAgo; !date.isAfter(now); date = date.plusDays(1)) {
            String dateStr = formatter.format(date);
            boolean found = false;
            for (Map<String, Object> map : resultList) {
                String mapDateStr = (String) map.get("day");
                if (dateStr.equals(mapDateStr)) {
                    Long count = (Long) map.get("orderCount");
                    result.add(new OrderShopDayNVO(mapDateStr, count.intValue()));
                    found = true;
                    break;
                }
            }
            if (!found) {
                result.add(new OrderShopDayNVO(dateStr, 0));
            }
        }
        return result;
    }

    @Override
    public BigDecimal getDayOrderMoney() {
        // 查询当天的起始时间
        LocalDateTime startOfDay = LocalDateTime.now().with(LocalTime.MIN);
        // 查询当天的结束时间
        LocalDateTime endOfDay = LocalDateTime.now().with(LocalTime.MAX);
        // 构建查询条件，查询finish_time在当天的销售记录，并统计总销售价格
        Map<String, Object> result = channelOrderMapper.selectTotalSellPrice(startOfDay, endOfDay);
        // 解析查询结果
        BigDecimal totalSalesToday =  result != null ? (BigDecimal) result.get("order_total") : BigDecimal.ZERO;
        return totalSalesToday;
    }

    @Override
    public Map<String, Integer> getCountShopYearOrderRefType() {
        return channelOrderMapper.getCountShopYearOrderRefType();
    }

    @Override
    public Map<String, Integer> getCountShopMonthOrderMoney() {
        return channelOrderMapper.getCountShopMonthOrderMoney();
    }

    public Integer getTradeOrderStatus(String statusStr) {
        for (TradeOrderStatusEnum status : TradeOrderStatusEnum.values()) {
            if (status.name().equalsIgnoreCase(statusStr)) {
                return status.getStatus();
            }
        }
        return null;
    }

    public Integer getTradeOrderAfterSaleStatus(String statusStr) {
        for (TradeOrderAfterSaleStatusEnum status : TradeOrderAfterSaleStatusEnum.values()) {
            if (status.name().equalsIgnoreCase(statusStr)) {
                return status.getStatus();
            }
        }
        return null;
    }

    public Integer getTradeOrderDeliveryStatus(String statusStr) {
        for (TradeOrderDeliveryStatusEnum status : TradeOrderDeliveryStatusEnum.values()) {
            if (status.name().equalsIgnoreCase(statusStr)) {
                return status.getStatus();
            }
        }
        return null;
    }
}
