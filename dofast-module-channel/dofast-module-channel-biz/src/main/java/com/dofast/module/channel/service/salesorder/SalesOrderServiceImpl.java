package com.dofast.module.channel.service.salesorder;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import com.dofast.module.channel.controller.admin.salesorder.vo.*;
import com.dofast.module.channel.dal.dataobject.salesorder.SalesOrderDO;
import com.dofast.framework.common.pojo.PageResult;

import com.dofast.module.channel.convert.salesorder.SalesOrderConvert;
import com.dofast.module.channel.dal.mysql.salesorder.SalesOrderMapper;

import static com.dofast.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.dofast.module.channel.enums.ErrorCodeConstants.*;

/**
 * 销售订单 Service 实现类
 *
 * @author 惠智造
 */
@Service
@Validated
public class SalesOrderServiceImpl implements SalesOrderService {

    @Resource
    private SalesOrderMapper salesOrderMapper;

    @Override
    public Long createSalesOrder(SalesOrderCreateReqVO createReqVO) {
        // 插入
        SalesOrderDO salesOrder = SalesOrderConvert.INSTANCE.convert(createReqVO);
        salesOrderMapper.insert(salesOrder);
        // 返回
        return salesOrder.getId();
    }

    @Override
    public void updateSalesOrder(SalesOrderUpdateReqVO updateReqVO) {
        // 校验存在
        validateSalesOrderExists(updateReqVO.getId());
        // 更新
        SalesOrderDO updateObj = SalesOrderConvert.INSTANCE.convert(updateReqVO);
        salesOrderMapper.updateById(updateObj);
    }

    @Override
    public void deleteSalesOrder(Long id) {
        // 校验存在
        validateSalesOrderExists(id);
        // 删除
        salesOrderMapper.deleteById(id);
    }

    private void validateSalesOrderExists(Long id) {
        if (salesOrderMapper.selectById(id) == null) {
            throw exception(SALES_ORDER_NOT_EXISTS);
        }
    }

    @Override
    public SalesOrderDO getSalesOrder(Long id) {
        return salesOrderMapper.selectById(id);
    }

    @Override
    public List<SalesOrderDO> getSalesOrderList(Collection<Long> ids) {
        return salesOrderMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<SalesOrderDO> getSalesOrderPage(SalesOrderPageReqVO pageReqVO) {
        return salesOrderMapper.selectPage(pageReqVO);
    }

    @Override
    public List<SalesOrderDO> getSalesOrderList(SalesOrderExportReqVO exportReqVO) {
        return salesOrderMapper.selectList(exportReqVO);
    }

}
