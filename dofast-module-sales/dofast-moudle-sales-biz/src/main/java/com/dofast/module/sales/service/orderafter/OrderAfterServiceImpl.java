package com.dofast.module.sales.service.orderafter;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import com.dofast.module.sales.controller.admin.orderafter.vo.*;
import com.dofast.module.sales.dal.dataobject.orderafter.OrderAfterDO;
import com.dofast.framework.common.pojo.PageResult;

import com.dofast.module.sales.convert.orderafter.OrderAfterConvert;
import com.dofast.module.sales.dal.mysql.orderafter.OrderAfterMapper;

import static com.dofast.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.dofast.module.sales.enums.ErrorCodeConstants.*;



import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.ListUtil;


/**
 * 售后流程表单 Service 实现类
 *
 * @author a1
 */
@Service
@Validated
public class OrderAfterServiceImpl implements OrderAfterService {

    @Resource
    private OrderAfterMapper orderAfterMapper;

    @Override
    public Long createOrderAfter(OrderAfterCreateReqVO createReqVO) {
        // 插入
        OrderAfterDO orderAfter = OrderAfterConvert.INSTANCE.convert(createReqVO);
        orderAfterMapper.insert(orderAfter);
        // 返回
        return orderAfter.getId();
    }

    @Override
    public void updateOrderAfter(OrderAfterUpdateReqVO updateReqVO) {
        // 校验存在
        validateOrderAfterExists(updateReqVO.getId());
        // 更新
        OrderAfterDO updateObj = OrderAfterConvert.INSTANCE.convert(updateReqVO);
        orderAfterMapper.updateById(updateObj);
    }

    @Override
    public void deleteOrderAfter(Long id) {
        // 校验存在
        validateOrderAfterExists(id);
        // 删除
        orderAfterMapper.deleteById(id);
    }

    private void validateOrderAfterExists(Long id) {
        if (orderAfterMapper.selectById(id) == null) {
            throw exception(ORDER_AFTER_NOT_EXISTS);
        }
    }

    @Override
    public OrderAfterDO getOrderAfter(Long id) {
        return orderAfterMapper.selectById(id);
    }

    @Override
    public List<OrderAfterDO> getOrderAfterList(Collection<Long> ids) {

        if (CollUtil.isEmpty(ids)) {
            return ListUtil.empty();
        }

        return orderAfterMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<OrderAfterDO> getOrderAfterPage(OrderAfterPageReqVO pageReqVO) {
        return orderAfterMapper.selectPage(pageReqVO);
    }

    @Override
    public List<OrderAfterDO> getOrderAfterList(OrderAfterExportReqVO exportReqVO) {
        return orderAfterMapper.selectList(exportReqVO);
    }

}
