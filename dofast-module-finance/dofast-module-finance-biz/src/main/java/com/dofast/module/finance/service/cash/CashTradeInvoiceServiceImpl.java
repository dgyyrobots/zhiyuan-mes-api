package com.dofast.module.finance.service.cash;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import com.dofast.module.finance.controller.admin.cash.vo.*;
import com.dofast.module.finance.dal.dataobject.cash.CashTradeInvoiceDO;
import com.dofast.framework.common.pojo.PageResult;

import com.dofast.module.finance.convert.cash.CashTradeInvoiceConvert;
import com.dofast.module.finance.dal.mysql.cash.CashTradeInvoiceMapper;

import static com.dofast.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.dofast.module.finance.enums.ErrorCodeConstants.*;

/**
 * 业务发票关联 Service 实现类
 *
 * @author 惠智造
 */
@Service
@Validated
public class CashTradeInvoiceServiceImpl implements CashTradeInvoiceService {

    @Resource
    private CashTradeInvoiceMapper cashTradeInvoiceMapper;

    @Override
    public Long createCashTradeInvoice(CashTradeInvoiceCreateReqVO createReqVO) {
        // 插入
        CashTradeInvoiceDO cashTradeInvoice = CashTradeInvoiceConvert.INSTANCE.convert(createReqVO);
        cashTradeInvoiceMapper.insert(cashTradeInvoice);
        // TODO 无主键返回值
        // 返回
        return new Long(0);
    }

    @Override
    public void updateCashTradeInvoice(CashTradeInvoiceUpdateReqVO updateReqVO) {
        // TODO 无主键更新
        // // 校验存在
        // validateCashTradeInvoiceExists(updateReqVO.getId());
        // // 更新
        // CashTradeInvoiceDO updateObj = CashTradeInvoiceConvert.INSTANCE.convert(updateReqVO);
        // cashTradeInvoiceMapper.updateById(updateObj);
    }

    @Override
    public void deleteCashTradeInvoice(Long id) {
        // TODO 无主键删除
        // 校验存在
        // validateCashTradeInvoiceExists(id);
        // 删除
        // cashTradeInvoiceMapper.deleteById(id);
    }

    private void validateCashTradeInvoiceExists(Long id) {
        if (cashTradeInvoiceMapper.selectById(id) == null) {
            throw exception(CASH_TRADE_INVOICE_NOT_EXISTS);
        }
    }

    @Override
    public CashTradeInvoiceDO getCashTradeInvoice(Long id) {
        // TODO 无主键获取
        // return cashTradeInvoiceMapper.selectById(id);
        return null;
    }

    @Override
    public List<CashTradeInvoiceDO> getCashTradeInvoiceList(Collection<Long> ids) {
        // return cashTradeInvoiceMapper.selectBatchIds(ids);
        // TODO 无主键列表
        return new ArrayList();
    }

    @Override
    public PageResult<CashTradeInvoiceDO> getCashTradeInvoicePage(CashTradeInvoicePageReqVO pageReqVO) {
        return cashTradeInvoiceMapper.selectPage(pageReqVO);
    }

    @Override
    public List<CashTradeInvoiceDO> getCashTradeInvoiceList(CashTradeInvoiceExportReqVO exportReqVO) {
        return cashTradeInvoiceMapper.selectList(exportReqVO);
    }

}
