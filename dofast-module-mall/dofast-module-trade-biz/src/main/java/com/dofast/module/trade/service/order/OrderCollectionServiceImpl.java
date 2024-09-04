package com.dofast.module.trade.service.order;

import com.dofast.framework.common.pojo.PageResult;
import com.dofast.module.trade.controller.admin.order.vo.*;
import com.dofast.module.trade.convert.order.OrderCollectionConvert;
import com.dofast.module.trade.dal.dataobject.order.OrderCollectionDO;
import com.dofast.module.trade.dal.mysql.order.OrderCollectionMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

import static com.dofast.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.dofast.module.trade.enums.ErrorCodeConstants.ORDER_COLLECTION_NOT_EXISTS;

/**
 * 采集任务 Service 实现类
 *
 * @author 惠智造
 */
@Service
@Validated
public class OrderCollectionServiceImpl implements OrderCollectionService {

    @Resource
    private OrderCollectionMapper orderCollectionMapper;

    @Override
    public Integer createOrderCollection(OrderCollectionCreateReqVO createReqVO) {
        // 插入
        OrderCollectionDO orderCollection = OrderCollectionConvert.INSTANCE.convert(createReqVO);
        orderCollectionMapper.insert(orderCollection);
        // 返回
        return orderCollection.getId();
    }

    @Override
    public void updateOrderCollection(OrderCollectionUpdateReqVO updateReqVO) {
        // 校验存在
        validateOrderCollectionExists(updateReqVO.getId());
        // 更新
        OrderCollectionDO updateObj = OrderCollectionConvert.INSTANCE.convert(updateReqVO);
        orderCollectionMapper.updateById(updateObj);
    }

    @Override
    public void updateOrderCollectionStatus(OrderCollectionUpdateStatusReqVO updateStatusReqVO) {
        validateOrderCollectionExists(updateStatusReqVO.getId());
        orderCollectionMapper.updateStatusById(updateStatusReqVO.getStatus(), updateStatusReqVO.getId());
    }

    @Override
    public void deleteOrderCollection(Integer id) {
        // 校验存在
        validateOrderCollectionExists(id);
        // 删除
        orderCollectionMapper.deleteById(id);
    }

    private void validateOrderCollectionExists(Integer id) {
        if (orderCollectionMapper.selectById(id) == null) {
            throw exception(ORDER_COLLECTION_NOT_EXISTS);
        }
    }

    @Override
    public OrderCollectionDO getOrderCollection(Integer id) {
        return orderCollectionMapper.selectById(id);
    }

    @Override
    public List<OrderCollectionDO> getOrderCollectionList(Collection<Integer> ids) {
        return orderCollectionMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<OrderCollectionDO> getOrderCollectionPage(OrderCollectionPageReqVO pageReqVO) {
        return orderCollectionMapper.selectPage(pageReqVO);
    }

    @Override
    public List<OrderCollectionDO> getOrderCollectionList(OrderCollectionExportReqVO exportReqVO) {
        return orderCollectionMapper.selectList(exportReqVO);
    }

}
