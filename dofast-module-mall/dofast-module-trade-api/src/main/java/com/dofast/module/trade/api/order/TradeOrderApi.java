package com.dofast.module.trade.api.order;

import com.dofast.module.trade.api.order.dto.TradeOrderRespDTO;
import com.dofast.module.trade.api.order.dto.TradeOrderSummaryRespDTO;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

/**
 * 订单 API 接口
 *
 * @author HUIHUI
 */
public interface TradeOrderApi {


//    public Collection<Long> getOrderIdByMixinOrderId(Long mixinOrderId);



    /**
     * 获得订单列表
     *
     * @param ids 订单编号数组
     * @return 订单列表
     */
    List<TradeOrderRespDTO> getOrderList(Collection<Long> ids);

    /**
     * 获得订单
     *
     * @param id 订单编号
     * @return 订单
     */
    TradeOrderRespDTO getOrder(Long id);

    // TODO 芋艿：看看是不是可以删除掉；
    /**
     * 获取订单状态
     *
     * @param id 订单编号
     * @return 订单状态
     */

//    Integer getOrderStatus(Long id);

    Integer getOrderStatus(Long id);


    /**
     * 获取订单统计
     *
     * @param beginTime 起始时间
     * @param endTime   截止时间
     * @return 订单统计结果
     */

//    TradeOrderSummaryRespDTO getOrderSummary(LocalDateTime beginTime, LocalDateTime endTime);
    // TODO 芋艿：需要优化下；
    /**
     * 取消支付订单
     *
     * @param userId  用户编号
     * @param orderId 订单编号
     */
    void cancelPaidOrder(Long userId, Long orderId);

    TradeOrderSummaryRespDTO getOrderSummary(LocalDateTime beginTime, LocalDateTime endTime);


}
