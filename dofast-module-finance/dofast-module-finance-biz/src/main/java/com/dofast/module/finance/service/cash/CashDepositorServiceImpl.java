package com.dofast.module.finance.service.cash;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import com.dofast.module.finance.controller.admin.cash.vo.*;
import com.dofast.module.finance.dal.dataobject.cash.CashDepositorDO;
import com.dofast.framework.common.pojo.PageResult;

import com.dofast.module.finance.convert.cash.CashDepositorConvert;
import com.dofast.module.finance.dal.mysql.cash.CashDepositorMapper;

import static com.dofast.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.dofast.module.finance.enums.ErrorCodeConstants.*;

/**
 * 资金账号 Service 实现类
 *
 * @author 惠智造
 */
@Service
@Validated
public class CashDepositorServiceImpl implements CashDepositorService {

    @Resource
    private CashDepositorMapper cashDepositorMapper;

    @Override
    public Long createCashDepositor(CashDepositorCreateReqVO createReqVO) {
        // 插入
        CashDepositorDO cashDepositor = CashDepositorConvert.INSTANCE.convert(createReqVO);
        cashDepositorMapper.insert(cashDepositor);
        // 返回
        return cashDepositor.getId();
    }

    @Override
    public void updateCashDepositor(CashDepositorUpdateReqVO updateReqVO) {
        // 校验存在
        validateCashDepositorExists(updateReqVO.getId());
        // 更新
        CashDepositorDO updateObj = CashDepositorConvert.INSTANCE.convert(updateReqVO);
        cashDepositorMapper.updateById(updateObj);
    }

    @Override
    public void deleteCashDepositor(Long id) {
        // 校验存在
        validateCashDepositorExists(id);
        // 删除
        cashDepositorMapper.deleteById(id);
    }

    private void validateCashDepositorExists(Long id) {
        if (cashDepositorMapper.selectById(id) == null) {
            throw exception(CASH_DEPOSITOR_NOT_EXISTS);
        }
    }

    @Override
    public CashDepositorDO getCashDepositor(Long id) {
        return cashDepositorMapper.selectById(id);
    }

    @Override
    public List<CashDepositorDO> getCashDepositorList(Collection<Long> ids) {
        return cashDepositorMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<CashDepositorDO> getCashDepositorPage(CashDepositorPageReqVO pageReqVO) {
        return cashDepositorMapper.selectPage(pageReqVO);
    }

    @Override
    public List<CashDepositorDO> getCashDepositorList(CashDepositorExportReqVO exportReqVO) {
        return cashDepositorMapper.selectList(exportReqVO);
    }

}
