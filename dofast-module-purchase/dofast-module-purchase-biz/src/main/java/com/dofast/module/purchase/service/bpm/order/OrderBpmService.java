package com.dofast.module.purchase.service.bpm.order;

import com.dofast.framework.common.pojo.PageResult;
import com.dofast.module.purchase.controller.admin.bpm.order.vo.OrderBpmCreateReqVO;
import com.dofast.module.purchase.controller.admin.bpm.order.vo.OrderBpmPageReqVO;
import com.dofast.module.purchase.controller.admin.invoice.vo.InvoiceCreateReqVO;
import com.dofast.module.purchase.controller.admin.invoice.vo.InvoicePageReqVO;
import com.dofast.module.purchase.controller.admin.order.vo.OrderCreateReqVO;
import com.dofast.module.purchase.controller.admin.order.vo.OrderPageReqVO;
import com.dofast.module.purchase.dal.dataobject.invoice.InvoiceDO;
import com.dofast.module.purchase.dal.dataobject.order.OrderDO;

import javax.validation.Valid;

public interface OrderBpmService {

    /**
     * 创建采购订单信息
     *
     * @param userId 用户编号
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createOrder(Long userId, @Valid OrderBpmCreateReqVO createReqVO);

    /**
     * 更新采购订单的状态
     *
     * @param id 编号
     * @param result 结果
     */
    void updateOrderResult(Long id, Integer result);

    /**
     * 获得采购订单信息
     *
     * @param id 编号
     * @return 请假申请
     */
    OrderDO getOrder(Long id);

    /**
     * 获得采购订单信息分页
     *
     * @param userId 用户编号
     * @param pageReqVO 分页查询
     * @return 请假申请分页
     */
    PageResult<OrderDO> getOrderPage(Long userId, OrderBpmPageReqVO pageReqVO);
}
