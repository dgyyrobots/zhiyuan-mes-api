package com.dofast.module.finance.service.cash;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import com.dofast.module.finance.controller.admin.cash.vo.*;
import com.dofast.module.finance.dal.dataobject.cash.CashFundDO;
import com.dofast.framework.common.pojo.PageResult;

import com.dofast.module.finance.convert.cash.CashFundConvert;
import com.dofast.module.finance.dal.mysql.cash.CashFundMapper;

import static com.dofast.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.dofast.module.finance.enums.ErrorCodeConstants.*;

/**
 * 财务退款 Service 实现类
 *
 * @author 惠智造
 */
@Service
@Validated
public class CashFundServiceImpl implements CashFundService {

    @Resource
    private CashFundMapper cashFundMapper;

    @Override
    public Long createCashFund(CashFundCreateReqVO createReqVO) {
        // 插入
        CashFundDO cashFund = CashFundConvert.INSTANCE.convert(createReqVO);
        cashFundMapper.insert(cashFund);
        // 返回
        return cashFund.getId();
    }

    @Override
    public void updateCashFund(CashFundUpdateReqVO updateReqVO) {
        // 校验存在
        validateCashFundExists(updateReqVO.getId());
        // 更新
        CashFundDO updateObj = CashFundConvert.INSTANCE.convert(updateReqVO);
        cashFundMapper.updateById(updateObj);
    }

    @Override
    public void deleteCashFund(Long id) {
        // 校验存在
        validateCashFundExists(id);
        // 删除
        cashFundMapper.deleteById(id);
    }

    private void validateCashFundExists(Long id) {
        if (cashFundMapper.selectById(id) == null) {
            throw exception(CASH_FUND_NOT_EXISTS);
        }
    }

    @Override
    public CashFundDO getCashFund(Long id) {
        return cashFundMapper.selectById(id);
    }

    @Override
    public List<CashFundDO> getCashFundList(Collection<Long> ids) {
        return cashFundMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<CashFundDO> getCashFundPage(CashFundPageReqVO pageReqVO) {
        return cashFundMapper.selectPage(pageReqVO);
    }

    @Override
    public List<CashFundDO> getCashFundList(CashFundExportReqVO exportReqVO) {
        return cashFundMapper.selectList(exportReqVO);
    }

}
