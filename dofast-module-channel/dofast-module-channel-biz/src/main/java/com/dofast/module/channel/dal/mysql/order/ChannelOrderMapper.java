package com.dofast.module.channel.dal.mysql.order;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dofast.framework.common.pojo.PageResult;
import com.dofast.framework.mybatis.core.mapper.BaseMapperX;
import com.dofast.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.dofast.module.channel.api.order.dto.OrderBaseDTO;
import com.dofast.module.channel.controller.admin.order.vo.*;
import com.dofast.module.channel.dal.dataobject.order.OrderDO;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dofast.module.channel.dal.dataobject.salesorder.SalesOrderDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 主订单 Mapper
 *
 * @author 惠智造
 */
@Mapper
public interface ChannelOrderMapper extends BaseMapperX<OrderDO> {
    //根据ChannelOrder的id和状态修改ChannelOrder的信息
    default int updateByIdAndStatus(Integer id, String status, OrderDO update) {
        return update(update, new LambdaUpdateWrapper<OrderDO>()
                .eq(OrderDO::getId, id).eq(OrderDO::getStatus, status));
    }


    //获取主订单分页使用-获取订单分页
    default PageResult<OrderDO> selectPageOrder(OrderPageReqVO reqVO){
        return selectPage(reqVO, new LambdaQueryWrapperX<OrderDO>()
                .eqIfPresent(OrderDO::getRefOid, reqVO.getRefOid())
                .eqIfPresent(OrderDO::getPosCode, reqVO.getPosCode())
                .eqIfPresent(OrderDO::getRefType, reqVO.getRefType())
                .eqIfPresent(OrderDO::getStatus, reqVO.getStatus())
                .likeIfPresent(OrderDO::getOpenBuyerNick, reqVO.getOpenBuyerNick())
                .betweenIfPresent(OrderDO::getOrderTime, reqVO.getCreateTime(), reqVO.getFinishTime())
                .orderByDesc(OrderDO::getId));
    }

    default PageResult<OrderDO> selectPage(OrderPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<OrderDO>()
                .eqIfPresent(OrderDO::getRefOid, reqVO.getRefOid())
                .eqIfPresent(OrderDO::getPosCode, reqVO.getPosCode())
                .eqIfPresent(OrderDO::getRefType, reqVO.getRefType())
                .eqIfPresent(OrderDO::getTotalFee, reqVO.getTotalFee())
                .eqIfPresent(OrderDO::getPayment, reqVO.getPayment())
                .eqIfPresent(OrderDO::getReceivedPayment, reqVO.getReceivedPayment())
                .eqIfPresent(OrderDO::getTotalPrice, reqVO.getTotalPrice())
                .eqIfPresent(OrderDO::getTotalSellPrice, reqVO.getTotalSellPrice())
                .eqIfPresent(OrderDO::getPostFee, reqVO.getPostFee())
                .eqIfPresent(OrderDO::getServiceFee, reqVO.getServiceFee())
                .eqIfPresent(OrderDO::getDiscountFee, reqVO.getDiscountFee())
                .betweenIfPresent(OrderDO::getOrderTime, reqVO.getOrderTime())
                .betweenIfPresent(OrderDO::getModifyTime, reqVO.getModifyTime())
                .betweenIfPresent(OrderDO::getPayTime, reqVO.getPayTime())
                .betweenIfPresent(OrderDO::getShippingTime, reqVO.getShippingTime())
                .betweenIfPresent(OrderDO::getFinishTime, reqVO.getFinishTime())
                .eqIfPresent(OrderDO::getReceiverCountry, reqVO.getReceiverCountry())
                .eqIfPresent(OrderDO::getReceiverState, reqVO.getReceiverState())
                .eqIfPresent(OrderDO::getReceiverCity, reqVO.getReceiverCity())
                .eqIfPresent(OrderDO::getReceiverDistrict, reqVO.getReceiverDistrict())
                .eqIfPresent(OrderDO::getReceiverTown, reqVO.getReceiverTown())
                .eqIfPresent(OrderDO::getReceiverId, reqVO.getReceiverId())
                .eqIfPresent(OrderDO::getStatus, reqVO.getStatus())
                .eqIfPresent(OrderDO::getType, reqVO.getType())
                .eqIfPresent(OrderDO::getRefundStatus, reqVO.getRefundStatus())
                .eqIfPresent(OrderDO::getFlag, reqVO.getFlag())
                .eqIfPresent(OrderDO::getSellerMemo, reqVO.getSellerMemo())
                .eqIfPresent(OrderDO::getBuyerMemo, reqVO.getBuyerMemo())
                .eqIfPresent(OrderDO::getOpenSellerNick, reqVO.getOpenSellerNick())
                .eqIfPresent(OrderDO::getOpenBuyerNick, reqVO.getOpenBuyerNick())
                .eqIfPresent(OrderDO::getOpenBuyerId, reqVO.getOpenBuyerId())
                .betweenIfPresent(OrderDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(OrderDO::getLogisticsPartnerCode, reqVO.getLogisticsPartnerCode())
                .eqIfPresent(OrderDO::getLogisticsOrderNo, reqVO.getLogisticsOrderNo())
                .eqIfPresent(OrderDO::getRefWhsCode, reqVO.getRefWhsCode())
                .betweenIfPresent(OrderDO::getDeliveryTime, reqVO.getDeliveryTime())
                .betweenIfPresent(OrderDO::getLatestDeliveryTime, reqVO.getLatestDeliveryTime())
                .eqIfPresent(OrderDO::getProps, reqVO.getProps())
                .eqIfPresent(OrderDO::getMark2, reqVO.getMark2())
                .eqIfPresent(OrderDO::getBusinessType, reqVO.getBusinessType())
                .eqIfPresent(OrderDO::getUserId, reqVO.getUserId())
                .eqIfPresent(OrderDO::getAddressId, reqVO.getAddressId())
                .eqIfPresent(OrderDO::getIsTranslate, reqVO.getIsTranslate())
                .orderByDesc(OrderDO::getId));
    }

    default List<OrderDO> selectList(OrderExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<OrderDO>()
                .eqIfPresent(OrderDO::getRefOid, reqVO.getRefOid())
                .eqIfPresent(OrderDO::getPosCode, reqVO.getPosCode())
                .eqIfPresent(OrderDO::getRefType, reqVO.getRefType())
                .eqIfPresent(OrderDO::getTotalFee, reqVO.getTotalFee())
                .eqIfPresent(OrderDO::getPayment, reqVO.getPayment())
                .eqIfPresent(OrderDO::getReceivedPayment, reqVO.getReceivedPayment())
                .eqIfPresent(OrderDO::getTotalPrice, reqVO.getTotalPrice())
                .eqIfPresent(OrderDO::getTotalSellPrice, reqVO.getTotalSellPrice())
                .eqIfPresent(OrderDO::getPostFee, reqVO.getPostFee())
                .eqIfPresent(OrderDO::getServiceFee, reqVO.getServiceFee())
                .eqIfPresent(OrderDO::getDiscountFee, reqVO.getDiscountFee())
                .betweenIfPresent(OrderDO::getOrderTime, reqVO.getOrderTime())
                .betweenIfPresent(OrderDO::getModifyTime, reqVO.getModifyTime())
                .betweenIfPresent(OrderDO::getPayTime, reqVO.getPayTime())
                .betweenIfPresent(OrderDO::getShippingTime, reqVO.getShippingTime())
                .betweenIfPresent(OrderDO::getFinishTime, reqVO.getFinishTime())
                .eqIfPresent(OrderDO::getReceiverCountry, reqVO.getReceiverCountry())
                .eqIfPresent(OrderDO::getReceiverState, reqVO.getReceiverState())
                .eqIfPresent(OrderDO::getReceiverCity, reqVO.getReceiverCity())
                .eqIfPresent(OrderDO::getReceiverDistrict, reqVO.getReceiverDistrict())
                .eqIfPresent(OrderDO::getReceiverTown, reqVO.getReceiverTown())
                .eqIfPresent(OrderDO::getReceiverId, reqVO.getReceiverId())
                .eqIfPresent(OrderDO::getStatus, reqVO.getStatus())
                .eqIfPresent(OrderDO::getType, reqVO.getType())
                .eqIfPresent(OrderDO::getRefundStatus, reqVO.getRefundStatus())
                .eqIfPresent(OrderDO::getFlag, reqVO.getFlag())
                .eqIfPresent(OrderDO::getSellerMemo, reqVO.getSellerMemo())
                .eqIfPresent(OrderDO::getBuyerMemo, reqVO.getBuyerMemo())
                .eqIfPresent(OrderDO::getOpenSellerNick, reqVO.getOpenSellerNick())
                .eqIfPresent(OrderDO::getOpenBuyerNick, reqVO.getOpenBuyerNick())
                .eqIfPresent(OrderDO::getOpenBuyerId, reqVO.getOpenBuyerId())
                .betweenIfPresent(OrderDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(OrderDO::getLogisticsPartnerCode, reqVO.getLogisticsPartnerCode())
                .eqIfPresent(OrderDO::getLogisticsOrderNo, reqVO.getLogisticsOrderNo())
                .eqIfPresent(OrderDO::getRefWhsCode, reqVO.getRefWhsCode())
                .betweenIfPresent(OrderDO::getDeliveryTime, reqVO.getDeliveryTime())
                .betweenIfPresent(OrderDO::getLatestDeliveryTime, reqVO.getLatestDeliveryTime())
                .eqIfPresent(OrderDO::getProps, reqVO.getProps())
                .eqIfPresent(OrderDO::getMark2, reqVO.getMark2())
                .eqIfPresent(OrderDO::getBusinessType, reqVO.getBusinessType())
                .eqIfPresent(OrderDO::getUserId, reqVO.getUserId())
                .eqIfPresent(OrderDO::getAddressId, reqVO.getAddressId())
                .eqIfPresent(OrderDO::getIsTranslate, reqVO.getIsTranslate())
                .orderByDesc(OrderDO::getId));
    }

//    default int update(OrderDO updateObj) {
//        return update(updateObj, new LambdaQueryWrapperX<OrderDO>()
//                .eq(OrderDO::getId, updateObj.getId())
//    }

    /**
     * 查询今日，昨日订单数量
     * @return 订单数量和售价总和
     */
    default Map<String, Object> selectOrderNVO(String shopId, LocalDateTime startDateTime, LocalDateTime endDateTime){
        // 创建查询包装器
        QueryWrapper<OrderDO> wrapper = new QueryWrapper<>();
        if(!shopId.equals("0")){
            wrapper.eq("pos_code", shopId);
        }
        // 添加查询条件，筛选指定日期的订单
        wrapper.between("order_time", Timestamp.valueOf(startDateTime), Timestamp.valueOf(endDateTime));
        // 添加分组和统计函数，统计订单数量和售价总和
        wrapper.select("COUNT(*) AS order_count");
        // 执行查询
        Map<String, Object> result = selectMaps(wrapper).get(0);
        return result;
    }

    /**
     * 查询全部订单数量和售价总和
     * @return 订单数量和售价总和
     */
    default Map<String, Object> selectAllOrderNVO(String shopId){
        // 创建查询包装器
        QueryWrapper<OrderDO> wrapper = new QueryWrapper<>();
        if(!shopId.equals("0")){
            wrapper.eq("pos_code", shopId);
        }
        // 添加分组和统计函数，统计订单数量和售价总和
        wrapper.select("COUNT(*) AS order_count", "SUM(total_sell_price) AS order_total");
        // 执行查询
        Map<String, Object> result = selectMaps(wrapper).get(0);
        return result;
    }


    /**
     * 查询七天内每日的订单量
     *
     * @param shopId 商铺编码
     * @param startDate 开始时间(当前时间前6天)
     * @param endDate 当前时间的日期
     * @return 存放有日期和统计数据的List
     */
    default List<Map<String, Object>> selectOrderDay(String shopId, String startDate, String endDate) {
        // SQL 查询
        QueryWrapper<OrderDO> wrapper = new QueryWrapper<>();
        if (shopId.equals("0")){
            wrapper.select("date_format(order_time, '%Y-%m-%d') as day", "count(*) as orderCount")
                    .between("order_time", startDate, endDate)
                    .groupBy("date_format(order_time, '%Y-%m-%d')");
        }else {
            // 添加条件
            wrapper.select("date_format(order_time, '%Y-%m-%d') as day", "count(*) as orderCount")
                    .between("order_time", startDate, endDate)
                    .eq("pos_code", shopId)
                    .groupBy("date_format(order_time, '%Y-%m-%d')");
        }
        // 执行查询
        List<Map<String, Object>> resultList = selectMaps(wrapper);
        return resultList;
    }

    default Map<String, Object> selectTotalSellPrice(LocalDateTime start, LocalDateTime end){
        QueryWrapper<OrderDO> wrapper = new QueryWrapper<>();
        wrapper.select("SUM(total_sell_price) AS order_total")
                .between("finish_time", start, end);
        Map<String, Object> result = selectMaps(wrapper).get(0);
        return result;
    }

    default Map<String, Integer> getCountShopYearOrderRefType(){
        QueryWrapper<OrderDO> wrapper = new QueryWrapper<>();
        wrapper.select("ref_type", "COUNT(*) as total")
                .groupBy("ref_type")
                .apply("YEAR(create_time) = YEAR(NOW())"); // 限定时间为今年

        List<Map<String, Object>> result = selectMaps(wrapper);
        Map<String, Integer> resultMap = new HashMap<>();
        for (Map<String, Object> map : result) {
            String refType = (String) map.get("ref_type");
            Integer total = ((Long) map.get("total")).intValue();
            resultMap.put(refType, total);
        }
        return resultMap;
    }

    default Map<String, Integer> getCountShopMonthOrderMoney(){
        QueryWrapper<OrderDO> wrapper = new QueryWrapper<>();
        wrapper.select("DATE_FORMAT(create_time, '%Y-%m') AS month", "SUM(total_fee) AS total")
                .groupBy("month")
                .apply("YEAR(create_time) = YEAR(NOW())"); // 限定时间为今年

        List<Map<String, Object>> result = selectMaps(wrapper);

        Map<String, Integer> resultMap = new HashMap<>();
        if (result == null || result.isEmpty()) return resultMap;
        for (Map<String, Object> map : result) {
            String month = (String) map.get("month");
            BigDecimal total = (BigDecimal) map.get("total");
            resultMap.put(month, total.intValue());
        }

        return resultMap;
    }



}
