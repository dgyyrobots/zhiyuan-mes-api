package com.dofast.module.channel.service.order;

import java.math.BigDecimal;
import java.util.*;
import javax.validation.*;
import com.dofast.module.channel.controller.admin.order.vo.*;
import com.dofast.module.channel.dal.dataobject.order.OrderDO;
import com.dofast.framework.common.pojo.PageResult;

/**
 * 主订单 Service 接口
 *
 * @author 惠智造
 */
public interface ChannelOrderService {

    /**
     * 创建主订单
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Integer createOrder(@Valid OrderCreateReqVO createReqVO);

    /**
     * 接收推送主订单
     *
     * @param ArrayList<OrderPushReqVO> 创建信息
     * @return 编号
     */
    HashMap pushOrders(@Valid ArrayList<OrderReceiveVO> orderPushReqVOList) ;
    /**
     * 更新主订单
     *
     * @param updateReqVO 更新信息
     */
    void updateOrder(@Valid OrderUpdateReqVO updateReqVO);

    /**
     * 删除主订单
     *
     * @param id 编号
     */
    void deleteOrder(Integer id);

    /**
     * 获得主订单
     *
     * @param id 编号
     * @return 主订单
     */
    OrderDO getOrder(Integer id);

    /**
     * 获得主订单列表
     *
     * @param ids 编号
     * @return 主订单列表
     */
    List<OrderDO> getOrderList(Collection<Integer> ids);

    /**
     * 获得主订单分页
     *
     * @param pageReqVO 分页查询
     * @return 主订单分页
     */
    PageResult<OrderDOGoodsVO> getOrderPage(OrderListQueryVO orderListQueryVO);

    /**
     * 渠道订单转商城订单
     *
     * @param data
     */
    Long ChannelOrderToMallOrder(@Valid OrderToInnerShopVO data);
    /**
     * 获得主订单列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 主订单列表
     */
    List<OrderDO> getOrderList(OrderExportReqVO exportReqVO);

    /**
     * 获取订单总数量，今日数量和昨日数量以及总金额
     *
     * @return 订单数量和金额信息
     */
    OrdedrNumberVO getOrderNumber();

    /**
     * 获取店铺订单总数量，今日数量和昨日数量以及总金额
     *
     * @param shopId 查询条件
     * @return 店铺订单数量和金额信息
     */
    OrdedrNumberVO getShopOrderNumber(String shopId);

    /**
     * 获取店铺日订单总数量
     *
     * @param shopId 查询条件
     * @return 店铺日订单数量
     */
    List<OrderShopDayNVO> getShopDayOrderNumber(String shopId);

    /**
     * 获取店铺订单日销售金额
     *
     * @return
     */
    BigDecimal getDayOrderMoney();

    Map<String, Integer> getCountShopYearOrderRefType();

    Map<String, Integer> getCountShopMonthOrderMoney();
}
