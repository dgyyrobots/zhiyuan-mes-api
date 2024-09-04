package com.dofast.module.finance.service.cash;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import com.dofast.module.finance.controller.admin.cash.vo.*;
import com.dofast.module.finance.dal.dataobject.cash.CashInvoiceDO;
import com.dofast.framework.common.pojo.PageResult;

import com.dofast.module.finance.convert.cash.CashInvoiceConvert;
import com.dofast.module.finance.dal.mysql.cash.CashInvoiceMapper;

import static com.dofast.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.dofast.module.finance.enums.ErrorCodeConstants.*;

/**
 * 发票信息 Service 实现类
 *
 * @author 惠智造
 */
@Service
@Validated
public class CashInvoiceServiceImpl implements CashInvoiceService {

    @Resource
    private CashInvoiceMapper cashInvoiceMapper;

    @Override
    public Long createCashInvoice(CashInvoiceCreateReqVO createReqVO) {
        // 插入
        CashInvoiceDO cashInvoice = CashInvoiceConvert.INSTANCE.convert(createReqVO);
        cashInvoiceMapper.insert(cashInvoice);
        // 返回
        return cashInvoice.getId();
    }

    @Override
    public void updateCashInvoice(CashInvoiceUpdateReqVO updateReqVO) {
        // 校验存在
        validateCashInvoiceExists(updateReqVO.getId());
        // 更新
        CashInvoiceDO updateObj = CashInvoiceConvert.INSTANCE.convert(updateReqVO);
        cashInvoiceMapper.updateById(updateObj);
    }

    @Override
    public void deleteCashInvoice(Long id) {
        // 校验存在
        validateCashInvoiceExists(id);
        // 删除
        cashInvoiceMapper.deleteById(id);
    }

    private void validateCashInvoiceExists(Long id) {
        if (cashInvoiceMapper.selectById(id) == null) {
            throw exception(CASH_INVOICE_NOT_EXISTS);
        }
    }

    @Override
    public CashInvoiceDO getCashInvoice(Long id) {
        return cashInvoiceMapper.selectById(id);
    }

    @Override
    public List<CashInvoiceDO> getCashInvoiceList(Collection<Long> ids) {
        return cashInvoiceMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<CashInvoiceDO> getCashInvoicePage(CashInvoicePageReqVO pageReqVO) {
        return cashInvoiceMapper.selectPage(pageReqVO);
    }

    @Override
    public List<CashInvoiceDO> getCashInvoiceList(CashInvoiceExportReqVO exportReqVO) {
        return cashInvoiceMapper.selectList(exportReqVO);
    }

}
