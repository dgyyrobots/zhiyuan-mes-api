package com.dofast.module.purchase.service.order;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import com.dofast.module.purchase.controller.admin.order.vo.*;
import com.dofast.module.purchase.dal.dataobject.order.OrderDO;
import com.dofast.framework.common.pojo.PageResult;

import com.dofast.module.purchase.convert.order.OrderConvert;
import com.dofast.module.purchase.dal.mysql.order.PurchaseOrderMapper;

import static com.dofast.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.dofast.module.purchase.enums.ErrorCodeConstants.*;

/**
 * 采购订单 Service 实现类
 *
 * @author 惠智造
 */
@Service
@Validated
public class OrderServiceImpl implements OrderService {

    @Resource
    private PurchaseOrderMapper purchaseOrderMapper;

    @Override
    public Integer createOrder(OrderCreateReqVO createReqVO) {
        // 插入
        OrderDO order = OrderConvert.INSTANCE.convert(createReqVO);
        purchaseOrderMapper.insert(order);
        // 返回
        return order.getId();
    }

    @Override
    public void updateOrder(OrderUpdateReqVO updateReqVO) {
        // 校验存在
        validateOrderExists(updateReqVO.getId());
        // 更新
        OrderDO updateObj = OrderConvert.INSTANCE.convert(updateReqVO);
        purchaseOrderMapper.updateById(updateObj);
    }

    @Override
    public void deleteOrder(Integer id) {
        // 校验存在
        validateOrderExists(id);
        // 删除
        purchaseOrderMapper.deleteById(id);
    }

    private void validateOrderExists(Integer id) {
        if (purchaseOrderMapper.selectById(id) == null) {
            throw exception(ORDER_NOT_EXISTS);
        }
    }

    @Override
    public OrderDO getOrder(Integer id) {
        return purchaseOrderMapper.selectById(id);
    }

    @Override
    public OrderDO getOrder(String poNo) {
        return purchaseOrderMapper.selectOne(OrderDO::getPoNo, poNo);
    }


    @Override
    public List<OrderDO> getOrderList(Collection<Integer> ids) {
        return purchaseOrderMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<OrderDO> getOrderPage(OrderPageReqVO pageReqVO) {
        return purchaseOrderMapper.selectPage(pageReqVO);
    }

    @Override
    public List<OrderDO> getOrderList(OrderExportReqVO exportReqVO) {
        return purchaseOrderMapper.selectList(exportReqVO);
    }

}
