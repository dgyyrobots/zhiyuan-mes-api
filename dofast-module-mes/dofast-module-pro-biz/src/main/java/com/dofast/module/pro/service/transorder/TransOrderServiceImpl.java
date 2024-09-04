package com.dofast.module.pro.service.transorder;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import com.dofast.module.pro.controller.admin.transorder.vo.*;
import com.dofast.module.pro.dal.dataobject.transorder.TransOrderDO;
import com.dofast.framework.common.pojo.PageResult;

import com.dofast.module.pro.convert.transorder.TransOrderConvert;
import com.dofast.module.pro.dal.mysql.transorder.TransOrderMapper;

import static com.dofast.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.dofast.module.pro.enums.ErrorCodeConstants.*;

/**
 * 流转单 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class TransOrderServiceImpl implements TransOrderService {

    @Resource
    private TransOrderMapper transOrderMapper;

    @Override
    public Long createTransOrder(TransOrderCreateReqVO createReqVO) {
        // 插入
        TransOrderDO transOrder = TransOrderConvert.INSTANCE.convert(createReqVO);
        transOrderMapper.insert(transOrder);
        // 返回
        return transOrder.getId();
    }

    @Override
    public void updateTransOrder(TransOrderUpdateReqVO updateReqVO) {
        // 校验存在
        validateTransOrderExists(updateReqVO.getId());
        // 更新
        TransOrderDO updateObj = TransOrderConvert.INSTANCE.convert(updateReqVO);
        transOrderMapper.updateById(updateObj);
    }

    @Override
    public void deleteTransOrder(Long id) {
        // 校验存在
        validateTransOrderExists(id);
        // 删除
        transOrderMapper.deleteById(id);
    }

    private void validateTransOrderExists(Long id) {
        if (transOrderMapper.selectById(id) == null) {
            throw exception(TRANS_ORDER_NOT_EXISTS);
        }
    }

    @Override
    public TransOrderDO getTransOrder(Long id) {
        return transOrderMapper.selectById(id);
    }

    @Override
    public List<TransOrderDO> getTransOrderList(Collection<Long> ids) {
        return transOrderMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<TransOrderDO> getTransOrderPage(TransOrderPageReqVO pageReqVO) {
        return transOrderMapper.selectPage(pageReqVO);
    }

    @Override
    public List<TransOrderDO> getTransOrderList(TransOrderExportReqVO exportReqVO) {
        return transOrderMapper.selectList(exportReqVO);
    }

}
