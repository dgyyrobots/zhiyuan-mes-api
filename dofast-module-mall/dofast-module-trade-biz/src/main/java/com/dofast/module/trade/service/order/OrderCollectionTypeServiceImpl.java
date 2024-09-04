package com.dofast.module.trade.service.order;

import com.dofast.framework.common.pojo.PageResult;
import com.dofast.module.trade.controller.admin.order.vo.OrderCollectionTypeCreateReqVO;
import com.dofast.module.trade.controller.admin.order.vo.OrderCollectionTypeExportReqVO;
import com.dofast.module.trade.controller.admin.order.vo.OrderCollectionTypePageReqVO;
import com.dofast.module.trade.controller.admin.order.vo.OrderCollectionTypeUpdateReqVO;
import com.dofast.module.trade.convert.order.OrderCollectionTypeConvert;
import com.dofast.module.trade.dal.dataobject.order.OrderCollectionTypeDO;
import com.dofast.module.trade.dal.mysql.order.OrderCollectionTypeMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

import static com.dofast.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.dofast.module.trade.enums.ErrorCodeConstants.ORDER_COLLECTION_TYPE_NOT_EXISTS;

/**
 * 采集任务类型 Service 实现类
 *
 * @author 惠智造
 */
@Service
@Validated
public class OrderCollectionTypeServiceImpl implements OrderCollectionTypeService {

    @Resource
    private OrderCollectionTypeMapper orderCollectionTypeMapper;

    @Override
    public Long createOrderCollectionType(OrderCollectionTypeCreateReqVO createReqVO) {
        // 插入
        OrderCollectionTypeDO orderCollectionType = OrderCollectionTypeConvert.INSTANCE.convert(createReqVO);
        orderCollectionTypeMapper.insert(orderCollectionType);
        // 返回
        return orderCollectionType.getId();
    }

    @Override
    public void updateOrderCollectionType(OrderCollectionTypeUpdateReqVO updateReqVO) {
        // 校验存在
        validateOrderCollectionTypeExists(updateReqVO.getId());
        // 更新
        OrderCollectionTypeDO updateObj = OrderCollectionTypeConvert.INSTANCE.convert(updateReqVO);
        orderCollectionTypeMapper.updateById(updateObj);
    }

    @Override
    public void deleteOrderCollectionType(Long id) {
        // 校验存在
        validateOrderCollectionTypeExists(id);
        // 删除
        orderCollectionTypeMapper.deleteById(id);
    }

    private void validateOrderCollectionTypeExists(Long id) {
        if (orderCollectionTypeMapper.selectById(id) == null) {
            throw exception(ORDER_COLLECTION_TYPE_NOT_EXISTS);
        }
    }

    @Override
    public OrderCollectionTypeDO getOrderCollectionType(Long id) {
        return orderCollectionTypeMapper.selectById(id);
    }

    @Override
    public List<OrderCollectionTypeDO> getOrderCollectionTypeList(Collection<Long> ids) {
        return orderCollectionTypeMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<OrderCollectionTypeDO> getOrderCollectionTypePage(OrderCollectionTypePageReqVO pageReqVO) {
        return orderCollectionTypeMapper.selectPage(pageReqVO);
    }

    @Override
    public List<OrderCollectionTypeDO> getOrderCollectionTypeList(OrderCollectionTypeExportReqVO exportReqVO) {
        return orderCollectionTypeMapper.selectList(exportReqVO);
    }

}
