package com.dofast.module.trade.service.order;

import com.dofast.framework.common.pojo.PageResult;
import com.dofast.module.trade.controller.admin.order.vo.*;
import com.dofast.module.trade.controller.app.order.vo.AppTradeOrderCreateReqVO;
import com.dofast.module.trade.controller.app.order.vo.AppTradeOrderPageReqVO;
import com.dofast.module.trade.dal.dataobject.order.TradeOrderDO;
import com.dofast.module.trade.dal.dataobject.order.TradeOrderItemDO;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import static java.util.Collections.singleton;

/**
 * 交易订单 Service 接口
 *
 * @author LeeYan9
 * @since 2022-08-26
 */
public interface TradeOrderService {

    // ===== Order =====

    /**
     * 【会员】创建交易订单
     *
     * @param userId 登录用户
     * @param userIp 用户 IP 地址
     * @param createReqVO 创建交易订单请求模型
     * @return 交易订单的编号
     */
    Long createOrder(Long userId, String userIp, AppTradeOrderCreateReqVO createReqVO);

    Long createOrder(Long userId, String userIp, TradeOrderCreateReqVO createReqVO);

    /**
     * 更新交易订单已支付
     *
     * @param id 交易订单编号
     * @param payOrderId 支付订单编号
     */
    void updateOrderPaid(Long id, Long payOrderId);


    /**
     * 修改订单地址
     *
     * @param id
     * @param address
     */
    void updateOrderAddress(Long id, TradeOrderReceiverAddress address);

    /**
     * 调整价格
     * @param id
     * @param price
     */
    void updateAdjustPrice(Long id, Integer price);

    /**
     * 线下支付
     * @param id
     */
    void offlinePay(Long id,Long bandCard);
    /**
     * 【管理员】发货交易订单
     *
     * @param userId 管理员编号
     * @param deliveryReqVO 发货请求
     */
    void deliveryOrder(Long userId, TradeOrderDeliveryReqVO deliveryReqVO);

    /**
     * 【会员】收货交易订单
     *
     * @param userId 用户编号
     * @param id 订单编号
     */
    void receiveOrder(Long userId, Long id);

    /**
     * 获得指定编号的交易订单
     *
     * @param id 交易订单编号
     * @return 交易订单
     */
    TradeOrderDO getOrder(Long id);

    /**
     * 获得指定用户，指定的交易订单
     *
     * @param userId 用户编号
     * @param id 交易订单编号
     * @return 交易订单
     */
    TradeOrderDO getOrder(Long userId, Long id);

    /**
     * 【管理员】获得交易订单分页
     *
     * @param reqVO 分页请求
     * @return 交易订单
     */
    PageResult<TradeOrderDO> getOrderPage(TradeOrderPageReqVO reqVO);

    /**
     * 【会员】获得交易订单分页
     *
     * @param userId 用户编号
     * @param reqVO 分页请求
     * @return 交易订单
     */
    PageResult<TradeOrderDO> getOrderPage(Long userId, AppTradeOrderPageReqVO reqVO);

    // ===== Order Item =====

    /**
     * 获得指定用户，指定的交易订单项
     *
     * @param userId 用户编号
     * @param itemId 交易订单项编号
     * @return 交易订单项
     */
    TradeOrderItemDO getOrderItem(Long userId, Long itemId);

    /**
     * 更新交易订单项的售后状态
     *
     * @param id 交易订单项编号
     * @param oldAfterSaleStatus 当前售后状态；如果不符，更新后会抛出异常
     * @param newAfterSaleStatus 目标售后状态
     * @param refundPrice 退款金额；当订单项退款成功时，必须传递该值
     */
    void updateOrderItemAfterSaleStatus(Long id, Integer oldAfterSaleStatus,
                                        Integer newAfterSaleStatus, Integer refundPrice);

    /**
     * 根据交易订单项编号数组，查询交易订单项
     *
     * @param ids 交易订单项编号数组
     * @return 交易订单项数组
     */
    List<TradeOrderItemDO> getOrderItemList(Collection<Long> ids);

    /**
     * 根据交易订单编号数组，查询交易订单
     *
     * @param ids 交易订单编号数组
     * @return 交易订单数组
     */
    List<TradeOrderDO> getOrderList(Collection<Long> ids);

    /**
     * 根据交易订单编号数组，查询交易订单
     *
     * @param reqVO 交易订单条件
     * @return 交易订单数组
     */
    List<TradeOrderDO> getOrderList(TradeOrderListReqVO reqVO);

    /**
     * 根据交易订单编号，查询交易订单项
     *
     * @param orderId 交易订单编号
     * @return 交易订单项数组
     */
    default List<TradeOrderItemDO> getOrderItemListByOrderId(Long orderId) {
        return getOrderItemListByOrderId(singleton(orderId));
    }

    List<TradeOrderDO> getWaitDelivery(Integer status);

    /**
     * 根据交易订单编号数组，查询交易订单项
     *
     * @param orderIds 交易订单编号数组
     * @return 交易订单项数组
     */
    List<TradeOrderItemDO> getOrderItemListByOrderId(Collection<Long> orderIds);

    /**
     * 根据订单的ID去执行关闭订单的操作
     *
     * @param id 订单的ID
     * @return
     */
    Boolean closeOrder(Long id);

    /**
     * 确认收货
     *
     * @param id 订单ID
     * @return
     */
    Boolean confirmReceipt(Long id);

    /**
     * 修改业务员
     *
     * @param id 订单ID
     * @param systemUserId 销售员ID
     * @param systemUserName 销售员名称
     * @return
     */
    void updateSystemUser(Long id, Long systemUserId, String systemUserName);

    Map<String, Integer> getCountMonthMoneyThisYear();

    Map<String, Integer> getCountMonthMoneyLastYear();

    Long getCountByDay(String creatTime);

    /**
     * 获得交易订单列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 交易订单列表
     */
    List<TradeOrderDO> getOrderList(TradeOrderExportReqVO exportReqVO);

    Boolean updateOrderDeliveryType(TradeOrderUpdateDeliveryTypeReqVO reqVO);
}
