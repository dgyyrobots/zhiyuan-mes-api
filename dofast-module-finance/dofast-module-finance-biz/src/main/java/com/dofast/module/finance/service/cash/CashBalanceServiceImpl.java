package com.dofast.module.finance.service.cash;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import com.dofast.module.finance.controller.admin.cash.vo.*;
import com.dofast.module.finance.dal.dataobject.cash.CashBalanceDO;
import com.dofast.framework.common.pojo.PageResult;

import com.dofast.module.finance.convert.cash.CashBalanceConvert;
import com.dofast.module.finance.dal.mysql.cash.CashBalanceMapper;

import static com.dofast.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.dofast.module.finance.enums.ErrorCodeConstants.*;

/**
 * 现金余额 Service 实现类
 *
 * @author 惠智造
 */
@Service
@Validated
public class CashBalanceServiceImpl implements CashBalanceService {

    @Resource
    private CashBalanceMapper cashBalanceMapper;

    @Override
    public Long createCashBalance(CashBalanceCreateReqVO createReqVO) {
        // 插入
        CashBalanceDO cashBalance = CashBalanceConvert.INSTANCE.convert(createReqVO);
        cashBalanceMapper.insert(cashBalance);
        // 返回
        return cashBalance.getId();
    }

    @Override
    public void updateCashBalance(CashBalanceUpdateReqVO updateReqVO) {
        // 校验存在
        validateCashBalanceExists(updateReqVO.getId());
        // 更新
        CashBalanceDO updateObj = CashBalanceConvert.INSTANCE.convert(updateReqVO);
        cashBalanceMapper.updateById(updateObj);
    }

    @Override
    public void deleteCashBalance(Long id) {
        // 校验存在
        validateCashBalanceExists(id);
        // 删除
        cashBalanceMapper.deleteById(id);
    }

    private void validateCashBalanceExists(Long id) {
        if (cashBalanceMapper.selectById(id) == null) {
            throw exception(CASH_BALANCE_NOT_EXISTS);
        }
    }

    @Override
    public CashBalanceDO getCashBalance(Long id) {
        return cashBalanceMapper.selectById(id);
    }

    @Override
    public List<CashBalanceDO> getCashBalanceList(Collection<Long> ids) {
        return cashBalanceMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<CashBalanceDO> getCashBalancePage(CashBalancePageReqVO pageReqVO) {
        return cashBalanceMapper.selectPage(pageReqVO);
    }

    @Override
    public List<CashBalanceDO> getCashBalanceList(CashBalanceExportReqVO exportReqVO) {
        return cashBalanceMapper.selectList(exportReqVO);
    }

}
