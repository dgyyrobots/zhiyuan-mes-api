package com.dofast.module.purchase.service.retreatOrder;

import com.dofast.module.purchase.controller.admin.retreatOrder.vo.RetreatOrderCreateReqVO;
import com.dofast.module.purchase.controller.admin.retreatOrder.vo.RetreatOrderExportReqVO;
import com.dofast.module.purchase.controller.admin.retreatOrder.vo.RetreatOrderPageReqVO;
import com.dofast.module.purchase.controller.admin.retreatOrder.vo.RetreatOrderUpdateReqVO;
import com.dofast.module.purchase.convert.retreatOrder.RetreatOrderConvert;
import com.dofast.module.purchase.dal.dataobject.retreatOrder.RetreatOrderDO;
import com.dofast.module.purchase.dal.mysql.retreatOrder.RetreatOrderMapper;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import java.util.*;
import com.dofast.framework.common.pojo.PageResult;
import static com.dofast.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.dofast.module.purchase.enums.ErrorCodeConstants.*;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.ListUtil;


/**
 * ERP仓退单 Service 实现类
 *
 * @author 惠智造
 */
@Service
@Validated
public class RetreatOrderServiceImpl implements RetreatOrderService {

    @Resource
    private RetreatOrderMapper orderMapper;

    @Override
    public Long createOrder(RetreatOrderCreateReqVO createReqVO) {
        // 插入
        RetreatOrderDO order = RetreatOrderConvert.INSTANCE.convert(createReqVO);
        orderMapper.insert(order);
        // 返回
        return order.getId();
    }

    @Override
    public void updateOrder(RetreatOrderUpdateReqVO updateReqVO) {
        // 校验存在
        validateOrderExists(updateReqVO.getId());
        // 更新
        RetreatOrderDO updateObj = RetreatOrderConvert.INSTANCE.convert(updateReqVO);
        orderMapper.updateById(updateObj);
    }

    @Override
    public void deleteOrder(Long id) {
        // 校验存在
        validateOrderExists(id);
        // 删除
        orderMapper.deleteById(id);
    }

    private void validateOrderExists(Long id) {
        if (orderMapper.selectById(id) == null) {
            throw exception(ORDER_NOT_EXISTS);
        }
    }

    @Override
    public RetreatOrderDO getOrder(Long id) {
        return orderMapper.selectById(id);
    }

    @Override
    public RetreatOrderDO getOrder(String retreatCode){
        return orderMapper.selectOne(RetreatOrderDO::getRetreatCode, retreatCode);
    }

    @Override
    public List<RetreatOrderDO> getOrderList(Collection<Long> ids) {

        if (CollUtil.isEmpty(ids)) {
            return ListUtil.empty();
        }

        return orderMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<RetreatOrderDO> getOrderPage(RetreatOrderPageReqVO pageReqVO) {
        return orderMapper.selectPage(pageReqVO);
    }

    @Override
    public List<RetreatOrderDO> getOrderList(RetreatOrderExportReqVO exportReqVO) {
        return orderMapper.selectList(exportReqVO);
    }

    @Override
    public void updateBatch(List<RetreatOrderDO> retreatOrderDOS){
        orderMapper.updateBatch(retreatOrderDOS);
    }

    @Override
    public void insertBatch(List<RetreatOrderDO> retreatOrderDOS){
        orderMapper.insertBatch(retreatOrderDOS);
    }

}
