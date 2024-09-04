package com.dofast.module.trade.dal.mysql.order;


import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.dofast.framework.common.pojo.PageResult;
import com.dofast.framework.mybatis.core.dataobject.BaseDO;
import com.dofast.framework.mybatis.core.mapper.BaseMapperX;
import com.dofast.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.dofast.module.trade.controller.admin.order.vo.TradeOrderExportReqVO;
import com.dofast.module.trade.controller.admin.order.vo.TradeOrderPageReqVO;
import com.dofast.module.trade.controller.app.order.vo.AppTradeOrderPageReqVO;
import com.dofast.module.trade.dal.dataobject.order.TradeOrderDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.math.BigDecimal;
import java.util.*;
import com.dofast.framework.mybatis.core.query.MPJLambdaWrapperX;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Set;


@Mapper
public interface TradeOrderMapper extends BaseMapperX<TradeOrderDO> {

    default int updateByIdAndStatus(Long id, Integer status, TradeOrderDO update) {
        return update(update, new LambdaUpdateWrapper<TradeOrderDO>()
                .eq(TradeOrderDO::getId, id).eq(TradeOrderDO::getStatus, status));
    }


    default int updateById(Long id, TradeOrderDO update) {
        return update(update, new LambdaUpdateWrapper<TradeOrderDO>()
                .eq(TradeOrderDO::getId, id));
    }


    default TradeOrderDO selectByIdAndUserId(Long id, Long userId) {
        return selectOne(TradeOrderDO::getId, id, TradeOrderDO::getUserId, userId);
    }


    default PageResult<TradeOrderDO> selectPage(TradeOrderPageReqVO reqVO, Set<Long> userIds, List<Long> ids, List<Long> notIds) {
        return selectPage(reqVO, new LambdaQueryWrapperX<TradeOrderDO>()
                .likeIfPresent(TradeOrderDO::getNo, reqVO.getNo())
                .likeIfPresent(TradeOrderDO::getChannelOrderId, reqVO.getChannelOrderId())
                .likeIfPresent(TradeOrderDO::getChannelOrderNo, reqVO.getChannelOrderNo())
                .likeIfPresent(TradeOrderDO::getChannel, reqVO.getChannel())
                .likeIfPresent(TradeOrderDO::getChannelShopName, reqVO.getChannelShopName())
                .eqIfPresent(TradeOrderDO::getChannelShopId, reqVO.getChannelShopId())
                .eqIfPresent(TradeOrderDO::getUserId, reqVO.getUserId())
                .inIfPresent(TradeOrderDO::getUserId, userIds)
                .eqIfPresent(TradeOrderDO::getSystemUserId, reqVO.getSystemUserId())
                .eqIfPresent(TradeOrderDO::getSystemUserName, reqVO.getSystemUserName())
//                .eqIfPresent(TradeOrderDO::getMixinOrderId,reqVO.getMixinOrderId())




                .eqIfPresent(TradeOrderDO::getMixinOrderId,reqVO.getMixinOrderId())
                .likeIfPresent(TradeOrderDO::getReceiverName, reqVO.getReceiverName())
                .likeIfPresent(TradeOrderDO::getReceiverMobile, reqVO.getReceiverMobile())
                .eqIfPresent(TradeOrderDO::getType, reqVO.getType())
                .eqIfPresent(TradeOrderDO::getStatus, reqVO.getStatus())
                .eqIfPresent(TradeOrderDO::getPayChannelCode, reqVO.getPayChannelCode())
                .eqIfPresent(TradeOrderDO::getReceiverDetailAddress,reqVO.getReceiverDetailAddress())
                .betweenIfPresent(TradeOrderDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(TradeOrderDO::getSystemUserId, reqVO.getSystemUserId())
                .notInIfPresent(TradeOrderDO::getId, Collections.singleton(reqVO.getIdNotIn()))
                .and(!CollUtil.isEmpty(ids) || !CollUtil.isEmpty(notIds) || ids.size()>0 || notIds.size()>0, qw -> {
                    if (!CollUtil.isEmpty(ids)) {
                        qw.in(TradeOrderDO::getId,ids).or();
                    }
                    if (!CollUtil.isEmpty(notIds)) {
                        qw.notIn(TradeOrderDO::getId,notIds);
                    }
                })
                .and(reqVO.getUserIdIn() != null && reqVO.getReceiverMobileIn()!=null,
                        qw-> qw.in(TradeOrderDO::getUserId, reqVO.getUserIdIn())
                                .or().in(TradeOrderDO::getReceiverMobile, reqVO.getReceiverMobileIn())
                )
                .and(reqVO.getUserIdNotIn() != null && reqVO.getReceiverMobileNotIn()!=null,
                        qw-> qw.notIn(TradeOrderDO::getUserId, reqVO.getUserIdNotIn())
                                .notIn(TradeOrderDO::getReceiverMobile, reqVO.getReceiverMobileNotIn())
                )
                /*.and(reqVO.getMixinOrderId()!=null && reqVO.getMixinOrderId()==1 ,
                        qw->qw.in(TradeOrderDO::getPayed,1))
                .and(reqVO.getMixinOrderId()!=null && reqVO.getMixinOrderId()!=1,
                        qw->qw.notIn(TradeOrderDO::getPayed,1))*/
                .and(reqVO.getMixinOrderId()!=null,qw->{
                    qw.in(TradeOrderDO::getPayed,reqVO.getMixinOrderId());
                })
                .and(reqVO.getMixinOrderId()!=null && reqVO.getMixinOrderId()!=0 ,
                        qw->qw.notIn(TradeOrderDO::getMixinOrderId,0))
                .and(reqVO.getMixinOrderId()!=null && reqVO.getMixinOrderId()==0,
                        qw->qw.eq(TradeOrderDO::getMixinOrderId,0))
                .orderByDesc(TradeOrderDO::getCreateTime));
    }

    default PageResult<TradeOrderDO> selectPage(TradeOrderPageReqVO reqVO, Set<Long> userIds) {
        return selectPage(reqVO, new LambdaQueryWrapperX<TradeOrderDO>()
                .likeIfPresent(TradeOrderDO::getNo, reqVO.getNo())
                .eqIfPresent(TradeOrderDO::getUserId, reqVO.getUserId())
                .eqIfPresent(TradeOrderDO::getDeliveryType, reqVO.getDeliveryType())
                .inIfPresent(TradeOrderDO::getUserId, userIds)
                .likeIfPresent(TradeOrderDO::getReceiverDetailAddress,reqVO.getReceiverDetailAddress())
                .likeIfPresent(TradeOrderDO::getReceiverMobile,reqVO.getReceiverMobile())
                .likeIfPresent(TradeOrderDO::getReceiverName,reqVO.getReceiverName())
                .eqIfPresent(TradeOrderDO::getType, reqVO.getType())
                .eqIfPresent(TradeOrderDO::getStatus, reqVO.getStatus())
                .eqIfPresent(TradeOrderDO::getPayChannelCode, reqVO.getPayChannelCode())
                .eqIfPresent(TradeOrderDO::getTerminal, reqVO.getTerminal())
                .eqIfPresent(TradeOrderDO::getChannel,reqVO.getChannel())
                .betweenIfPresent(TradeOrderDO::getDeliveryTime,reqVO.getDeliveryTime())
                .eqIfPresent(TradeOrderDO::getChannelShopId,reqVO.getShopId())
                .eqIfPresent(TradeOrderDO::getLogisticsId, reqVO.getLogisticsId())
                .eqIfPresent(TradeOrderDO::getSystemUserName,reqVO.getSystemUserName())
                .inIfPresent(TradeOrderDO::getPickUpStoreId, reqVO.getPickUpStoreIds())
                .likeIfPresent(TradeOrderDO::getPickUpVerifyCode, reqVO.getPickUpVerifyCode())
                .betweenIfPresent(TradeOrderDO::getCreateTime, reqVO.getCreateTime())
                .and(reqVO.getMixinOrderId()!=null && reqVO.getMixinOrderId()!=0,
                        qw->qw.notIn(TradeOrderDO::getMixinOrderId,0))
                .and(reqVO.getMixinOrderId()!=null && reqVO.getMixinOrderId()==0,
                        qw->qw.eq(TradeOrderDO::getMixinOrderId,0))
                /*.and(reqVO.getDeliveryTime().length>0 && reqVO.getDeliveryTime()!=null,
                        qw->{
                    qw.ge(TradeOrderDO::getDeliveryTime,reqVO.getDeliveryTime()[0])
                            .le(TradeOrderDO::getDeliveryTime,reqVO.getDeliveryTime()[1]);
                        })*/
                .orderByDesc(TradeOrderDO::getId));
    }

    // TODO @疯狂：如果用 map 返回，要不这里直接用 TradeOrderSummaryRespVO 返回？也算合理，就当  sql 查询出这么个玩意~~
    default List<Map<String, Object>> selectOrderSummaryGroupByRefundStatus(TradeOrderPageReqVO reqVO, Set<Long> userIds) {
        return selectMaps(new MPJLambdaWrapperX<TradeOrderDO>()
                .selectAs(TradeOrderDO::getRefundStatus, TradeOrderDO::getRefundStatus)  // 售后状态
                .selectCount(TradeOrderDO::getId, "count") // 售后状态对应的数量
                .selectSum(TradeOrderDO::getPayPrice, "price")  // 售后状态对应的支付金额
                .likeIfPresent(TradeOrderDO::getNo, reqVO.getNo())
                .eqIfPresent(TradeOrderDO::getUserId, reqVO.getUserId())
                .eqIfPresent(TradeOrderDO::getDeliveryType, reqVO.getDeliveryType())
                .inIfPresent(TradeOrderDO::getUserId, userIds)
                .eqIfPresent(TradeOrderDO::getType, reqVO.getType())
                .eqIfPresent(TradeOrderDO::getStatus, reqVO.getStatus())
                .eqIfPresent(TradeOrderDO::getPayChannelCode, reqVO.getPayChannelCode())
                .eqIfPresent(TradeOrderDO::getTerminal, reqVO.getTerminal())
                .eqIfPresent(TradeOrderDO::getLogisticsId, reqVO.getLogisticsId())
                .inIfPresent(TradeOrderDO::getPickUpStoreId, reqVO.getPickUpStoreIds())
                .likeIfPresent(TradeOrderDO::getPickUpVerifyCode, reqVO.getPickUpVerifyCode())
                .betweenIfPresent(TradeOrderDO::getCreateTime, reqVO.getCreateTime())
                .groupBy(TradeOrderDO::getRefundStatus)); // 按售后状态分组
    }


    default PageResult<TradeOrderDO> selectPage(AppTradeOrderPageReqVO reqVO, Long userId) {
        return selectPage(reqVO, new LambdaQueryWrapperX<TradeOrderDO>()
                .eq(TradeOrderDO::getUserId, userId)
                .eqIfPresent(TradeOrderDO::getStatus, reqVO.getStatus())

                .orderByDesc(TradeOrderDO::getId)); // TODO 芋艿：未来不同的 status，不同的排序
    }

    @Select("SELECT LAST_INSERT_ID()")
    Long getLastInsertId();

    default Map<String, Integer> getCountMonthMoneyThisYear(){
        QueryWrapper<TradeOrderDO> wrapper = new QueryWrapper<>();
        wrapper.select("DATE_FORMAT(create_time, '%Y-%m') AS month", "SUM(pay_price) AS total")
                .groupBy("month")
                .apply("YEAR(create_time) = YEAR(NOW())"); // 限定时间为今年

        List<Map<String, Object>> result = selectMaps(wrapper);

        Map<String, Integer> resultMap = new HashMap<>();
        if (result == null || result.isEmpty()) return resultMap;
        for (Map<String, Object> map : result) {
            String month = (String) map.get("month");
            BigDecimal total = (BigDecimal) map.get("total");
            int totalAmount = total.divide(new BigDecimal(100)).intValue();
            resultMap.put(month, totalAmount);
        }

        return resultMap;
    }

    default Map<String, Integer> getCountMonthMoneyLastYear(){
        QueryWrapper<TradeOrderDO> wrapper = new QueryWrapper<>();
        wrapper.select("DATE_FORMAT(create_time, '%Y-%m') AS month", "SUM(pay_price) AS total")
                .groupBy("month")
                .apply("YEAR(create_time) = YEAR(NOW()) - 1"); // 限定时间为上一年

        List<Map<String, Object>> result = selectMaps(wrapper);

        Map<String, Integer> resultMap = new HashMap<>();
        if (result == null || result.isEmpty()) return resultMap;
        for (Map<String, Object> map : result) {
            String month = (String) map.get("month");
            BigDecimal total = (BigDecimal) map.get("total");
            int totalAmount = total.divide(new BigDecimal(100)).intValue();
            resultMap.put(month, totalAmount);
        }

        return resultMap;
    }

    default Long getCountBuDay(String time){
        return selectCount(new LambdaQueryWrapperX<TradeOrderDO>()
                .like(BaseDO::getCreateTime,time));
    }

    default Long selectCountByUserIdAndStatus(Long userId, Integer status, Boolean commentStatus) {
        return selectCount(new LambdaQueryWrapperX<TradeOrderDO>()
                .eq(TradeOrderDO::getUserId, userId)
                .eqIfPresent(TradeOrderDO::getStatus, status)
                .eqIfPresent(TradeOrderDO::getCommentStatus, commentStatus));
    }

    default TradeOrderDO selectOrderByIdAndUserId(Long orderId, Long loginUserId) {
        return selectOne(new LambdaQueryWrapperX<TradeOrderDO>()
                .eq(TradeOrderDO::getId, orderId)
                .eq(TradeOrderDO::getUserId, loginUserId));
    }

    default List<TradeOrderDO> selectListByStatusAndCreateTimeLt(Integer status, LocalDateTime createTime) {
        return selectList(new LambdaUpdateWrapper<TradeOrderDO>()
                .eq(TradeOrderDO::getStatus, status)
                .lt(TradeOrderDO::getCreateTime, createTime));
    }

    default List<TradeOrderDO> selectListByStatusAndDeliveryTimeLt(Integer status, LocalDateTime deliveryTime) {
        return selectList(new LambdaUpdateWrapper<TradeOrderDO>()
                .eq(TradeOrderDO::getStatus, status)
                .lt(TradeOrderDO::getDeliveryTime, deliveryTime));
    }

    default List<TradeOrderDO> selectListByStatusAndReceiveTimeLt(Integer status, LocalDateTime receive,
                                                                  Boolean commentStatus) {
        return selectList(new LambdaUpdateWrapper<TradeOrderDO>()
                .eq(TradeOrderDO::getStatus, status)
                .lt(TradeOrderDO::getReceiveTime, receive)
                .eq(TradeOrderDO::getCommentStatus, commentStatus));
    }

    default List<TradeOrderDO> selectListByUserIdAndSeckillActivityId(Long userId, Long seckillActivityId) {
        return selectList(new LambdaUpdateWrapper<>(TradeOrderDO.class)
                .eq(TradeOrderDO::getUserId, userId)
                .eq(TradeOrderDO::getSeckillActivityId, seckillActivityId));
    }

    default TradeOrderDO selectOneByPickUpVerifyCode(String pickUpVerifyCode) {
        return selectOne(TradeOrderDO::getPickUpVerifyCode, pickUpVerifyCode);
    }

    default TradeOrderDO selectByUserIdAndCombinationActivityIdAndStatus(Long userId, Long combinationActivityId, Integer status) {
        return selectOne(new LambdaQueryWrapperX<TradeOrderDO>()
                .eq(TradeOrderDO::getUserId, userId)
                .eq(TradeOrderDO::getStatus, status)
                .eq(TradeOrderDO::getCombinationActivityId, combinationActivityId)
        );

    }

    default List<TradeOrderDO> selectList(TradeOrderExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<TradeOrderDO>()
                .eqIfPresent(TradeOrderDO::getChannelOrderId, reqVO.getChannelOrderId())
                .eqIfPresent(TradeOrderDO::getNo, reqVO.getNo())
                .eqIfPresent(TradeOrderDO::getChannelOrderNo, reqVO.getChannelOrderNo())
                .eqIfPresent(TradeOrderDO::getType, reqVO.getType())
                .eqIfPresent(TradeOrderDO::getChannel, reqVO.getChannel())
                .eqIfPresent(TradeOrderDO::getChannelShopId, reqVO.getChannelShopId())
                .likeIfPresent(TradeOrderDO::getChannelShopName, reqVO.getChannelShopName())
                .eqIfPresent(TradeOrderDO::getTerminal, reqVO.getTerminal())
                .eqIfPresent(TradeOrderDO::getUserId, reqVO.getUserId())
                .eqIfPresent(TradeOrderDO::getUserIp, reqVO.getUserIp())
                .eqIfPresent(TradeOrderDO::getUserRemark, reqVO.getUserRemark())
                .eqIfPresent(TradeOrderDO::getStatus, reqVO.getStatus())
                .eqIfPresent(TradeOrderDO::getProductCount, reqVO.getProductCount())
                .eqIfPresent(TradeOrderDO::getCancelType, reqVO.getCancelType())
                .eqIfPresent(TradeOrderDO::getRemark, reqVO.getRemark())
                .eqIfPresent(TradeOrderDO::getPayOrderId, reqVO.getPayOrderId())
                .eqIfPresent(TradeOrderDO::getPayed, reqVO.getPayed())
                .betweenIfPresent(TradeOrderDO::getPayTime, reqVO.getPayTime())
                .eqIfPresent(TradeOrderDO::getPayChannelCode, reqVO.getPayChannelCode())
                .betweenIfPresent(TradeOrderDO::getFinishTime, reqVO.getFinishTime())
                .betweenIfPresent(TradeOrderDO::getCancelTime, reqVO.getCancelTime())
                .eqIfPresent(TradeOrderDO::getOriginalPrice, reqVO.getOriginalPrice())
                .eqIfPresent(TradeOrderDO::getOrderPrice, reqVO.getOrderPrice())
                .eqIfPresent(TradeOrderDO::getDiscountPrice, reqVO.getDiscountPrice())
                .eqIfPresent(TradeOrderDO::getDeliveryPrice, reqVO.getDeliveryPrice())
                .eqIfPresent(TradeOrderDO::getAdjustPrice, reqVO.getAdjustPrice())
                .eqIfPresent(TradeOrderDO::getPayPrice, reqVO.getPayPrice())
                .eqIfPresent(TradeOrderDO::getDeliveryTemplateId, reqVO.getDeliveryTemplateId())
                .eqIfPresent(TradeOrderDO::getLogisticsId, reqVO.getLogisticsId())
                .eqIfPresent(TradeOrderDO::getLogisticsNo, reqVO.getLogisticsNo())
                .eqIfPresent(TradeOrderDO::getDeliveryStatus, reqVO.getDeliveryStatus())
                .betweenIfPresent(TradeOrderDO::getDeliveryTime, reqVO.getDeliveryTime())
                .betweenIfPresent(TradeOrderDO::getReceiveTime, reqVO.getReceiveTime())
                .likeIfPresent(TradeOrderDO::getReceiverName, reqVO.getReceiverName())
                .eqIfPresent(TradeOrderDO::getReceiverMobile, reqVO.getReceiverMobile())
                .eqIfPresent(TradeOrderDO::getReceiverAreaId, reqVO.getReceiverAreaId())
                .eqIfPresent(TradeOrderDO::getReceiverPostCode, reqVO.getReceiverPostCode())
                .eqIfPresent(TradeOrderDO::getReceiverDetailAddress, reqVO.getReceiverDetailAddress())
                .eqIfPresent(TradeOrderDO::getAfterSaleStatus, reqVO.getAfterSaleStatus())
                .eqIfPresent(TradeOrderDO::getRefundPrice, reqVO.getRefundPrice())
                .eqIfPresent(TradeOrderDO::getCouponId, reqVO.getCouponId())
                .eqIfPresent(TradeOrderDO::getCouponPrice, reqVO.getCouponPrice())
                .eqIfPresent(TradeOrderDO::getPointPrice, reqVO.getPointPrice())
                .betweenIfPresent(TradeOrderDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(TradeOrderDO::getSystemUserId, reqVO.getSystemUserId())
                .likeIfPresent(TradeOrderDO::getSystemUserName, reqVO.getSystemUserName())
                .orderByDesc(TradeOrderDO::getId));
    }

}
