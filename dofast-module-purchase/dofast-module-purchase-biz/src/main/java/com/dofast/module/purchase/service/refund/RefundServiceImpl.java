package com.dofast.module.purchase.service.refund;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import com.dofast.module.purchase.controller.admin.refund.vo.*;
import com.dofast.module.purchase.dal.dataobject.refund.RefundDO;
import com.dofast.framework.common.pojo.PageResult;

import com.dofast.module.purchase.convert.refund.RefundConvert;
import com.dofast.module.purchase.dal.mysql.refund.PurchaseRefundMapper;

import static com.dofast.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.dofast.module.purchase.enums.ErrorCodeConstants.*;

/**
 * 采购退货 Service 实现类
 *
 * @author 惠智造
 */
@Service
@Validated
public class RefundServiceImpl implements RefundService {

    @Resource
    private PurchaseRefundMapper prurchaseRefundMapper;

    @Override
    public Integer createRefund(RefundCreateReqVO createReqVO) {
        // 插入
        RefundDO refund = RefundConvert.INSTANCE.convert(createReqVO);
        prurchaseRefundMapper.insert(refund);
        // 返回
        return refund.getId();
    }

    @Override
    public void updateRefund(RefundUpdateReqVO updateReqVO) {
        // 校验存在
        validateRefundExists(updateReqVO.getId());
        // 更新
        RefundDO updateObj = RefundConvert.INSTANCE.convert(updateReqVO);
        prurchaseRefundMapper.updateById(updateObj);
    }

    @Override
    public void deleteRefund(Integer id) {
        // 校验存在
        validateRefundExists(id);
        // 删除
        prurchaseRefundMapper.deleteById(id);
    }

    private void validateRefundExists(Integer id) {
        if (prurchaseRefundMapper.selectById(id) == null) {
            throw exception(REFUND_NOT_EXISTS);
        }
    }

    @Override
    public RefundDO getRefund(Integer id) {
        return prurchaseRefundMapper.selectById(id);
    }

    @Override
    public List<RefundDO> getRefundList(Collection<Integer> ids) {
        return prurchaseRefundMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<RefundDO> getRefundPage(RefundPageReqVO pageReqVO) {
        return prurchaseRefundMapper.selectPage(pageReqVO);
    }

    @Override
    public List<RefundDO> getRefundList(RefundExportReqVO exportReqVO) {
        return prurchaseRefundMapper.selectList(exportReqVO);
    }

}
