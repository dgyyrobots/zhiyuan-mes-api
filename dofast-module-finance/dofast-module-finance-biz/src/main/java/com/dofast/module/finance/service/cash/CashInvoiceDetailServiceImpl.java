package com.dofast.module.finance.service.cash;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import com.dofast.module.finance.controller.admin.cash.vo.*;
import com.dofast.module.finance.dal.dataobject.cash.CashInvoiceDetailDO;
import com.dofast.framework.common.pojo.PageResult;

import com.dofast.module.finance.convert.cash.CashInvoiceDetailConvert;
import com.dofast.module.finance.dal.mysql.cash.CashInvoiceDetailMapper;

import static com.dofast.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.dofast.module.finance.enums.ErrorCodeConstants.*;

/**
 * 财务发票明细 Service 实现类
 *
 * @author 惠智造
 */
@Service
@Validated
public class CashInvoiceDetailServiceImpl implements CashInvoiceDetailService {

    @Resource
    private CashInvoiceDetailMapper cashInvoiceDetailMapper;

    @Override
    public Long createCashInvoiceDetail(CashInvoiceDetailCreateReqVO createReqVO) {
        // 插入
        CashInvoiceDetailDO cashInvoiceDetail = CashInvoiceDetailConvert.INSTANCE.convert(createReqVO);
        cashInvoiceDetailMapper.insert(cashInvoiceDetail);
        // 返回
        return cashInvoiceDetail.getId();
    }

    @Override
    public void updateCashInvoiceDetail(CashInvoiceDetailUpdateReqVO updateReqVO) {
        // 校验存在
        validateCashInvoiceDetailExists(updateReqVO.getId());
        // 更新
        CashInvoiceDetailDO updateObj = CashInvoiceDetailConvert.INSTANCE.convert(updateReqVO);
        cashInvoiceDetailMapper.updateById(updateObj);
    }

    @Override
    public void deleteCashInvoiceDetail(Long id) {
        // 校验存在
        validateCashInvoiceDetailExists(id);
        // 删除
        cashInvoiceDetailMapper.deleteById(id);
    }

    private void validateCashInvoiceDetailExists(Long id) {
        if (cashInvoiceDetailMapper.selectById(id) == null) {
            throw exception(CASH_INVOICE_DETAIL_NOT_EXISTS);
        }
    }

    @Override
    public CashInvoiceDetailDO getCashInvoiceDetail(Long id) {
        return cashInvoiceDetailMapper.selectById(id);
    }

    @Override
    public List<CashInvoiceDetailDO> getCashInvoiceDetailList(Collection<Long> ids) {
        return cashInvoiceDetailMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<CashInvoiceDetailDO> getCashInvoiceDetailPage(CashInvoiceDetailPageReqVO pageReqVO) {
        return cashInvoiceDetailMapper.selectPage(pageReqVO);
    }

    @Override
    public List<CashInvoiceDetailDO> getCashInvoiceDetailList(CashInvoiceDetailExportReqVO exportReqVO) {
        return cashInvoiceDetailMapper.selectList(exportReqVO);
    }

}
