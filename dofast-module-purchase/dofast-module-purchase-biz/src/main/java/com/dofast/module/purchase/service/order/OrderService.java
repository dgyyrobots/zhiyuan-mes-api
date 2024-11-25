package com.dofast.module.purchase.service.order;

import java.util.*;
import javax.validation.*;
import com.dofast.module.purchase.controller.admin.order.vo.*;
import com.dofast.module.purchase.dal.dataobject.order.OrderDO;
import com.dofast.framework.common.pojo.PageResult;

/**
 * 采购订单 Service 接口
 *
 * @author 惠智造
 */
public interface OrderService {

    /**
     * 创建采购订单
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Integer createOrder(@Valid OrderCreateReqVO createReqVO);

    /**
     * 更新采购订单
     *
     * @param updateReqVO 更新信息
     */
    void updateOrder(@Valid OrderUpdateReqVO updateReqVO);

    /**
     * 删除采购订单
     *
     * @param id 编号
     */
    void deleteOrder(Integer id);

    /**
     * 获得采购订单
     *
     * @param id 编号
     * @return 采购订单
     */
    OrderDO getOrder(Integer id);

    /**
     * 获得采购订单
     *
     * @param poNo 编号
     * @return 采购订单
     */
    OrderDO getOrder(String poNo);


    /**
     * 获得采购订单列表
     *
     * @param ids 编号
     * @return 采购订单列表
     */
    List<OrderDO> getOrderList(Collection<Integer> ids);

    /**
     * 获得采购订单分页
     *
     * @param pageReqVO 分页查询
     * @return 采购订单分页
     */
    PageResult<OrderDO> getOrderPage(OrderPageReqVO pageReqVO);

    /**
     * 获得采购订单列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 采购订单列表
     */
    List<OrderDO> getOrderList(OrderExportReqVO exportReqVO);

}
