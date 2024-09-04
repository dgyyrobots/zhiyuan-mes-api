package com.dofast.module.finance.service.cash;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;
import com.dofast.module.finance.controller.admin.cash.vo.*;
import com.dofast.module.finance.dal.dataobject.cash.CashTradeDO;
import com.dofast.framework.common.pojo.PageResult;

import com.dofast.module.finance.convert.cash.CashTradeConvert;
import com.dofast.module.finance.dal.mysql.cash.CashTradeMapper;

import static com.dofast.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.dofast.module.finance.enums.ErrorCodeConstants.*;

/**
 * 财务流水 Service 实现类
 *
 * @author 惠智造
 */
@Service
@Validated
public class CashTradeServiceImpl implements CashTradeService {

    @Resource
    private CashTradeMapper cashTradeMapper;

    @Override
    public Long createCashTrade(CashTradeCreateReqVO createReqVO) {
        // 插入
        CashTradeDO cashTrade = CashTradeConvert.INSTANCE.convert(createReqVO);
        cashTradeMapper.insert(cashTrade);
        // 返回
        return cashTrade.getId();
    }

    @Override
    public void updateCashTrade(CashTradeUpdateReqVO updateReqVO) {
        // 校验存在
        validateCashTradeExists(updateReqVO.getId());
        // 更新
        CashTradeDO updateObj = CashTradeConvert.INSTANCE.convert(updateReqVO);
        cashTradeMapper.updateById(updateObj);
    }

    @Override
    public void deleteCashTrade(Long id) {
        // 校验存在
        validateCashTradeExists(id);
        // 删除
        cashTradeMapper.deleteById(id);
    }

    private void validateCashTradeExists(Long id) {
        if (cashTradeMapper.selectById(id) == null) {
            throw exception(CASH_TRADE_NOT_EXISTS);
        }
    }

    @Override
    public CashTradeDO getCashTrade(Long id) {
        return cashTradeMapper.selectById(id);
    }

    @Override
    public List<CashTradeDO> getCashTradeList(Collection<Long> ids) {
        return cashTradeMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<CashTradeDO> getCashTradePage(CashTradePageReqVO pageReqVO) {
        return cashTradeMapper.selectPage(pageReqVO);
    }

    @Override
    public List<CashTradeDO> getCashTradeList(CashTradeExportReqVO exportReqVO) {
        return cashTradeMapper.selectList(exportReqVO);
    }

    @Override
    public BigDecimal getDayOrderMoney(Integer type) {
        // 查询当天的起始时间
        LocalDateTime startOfDay = LocalDateTime.now().with(LocalTime.MIN);
        // 查询当天的结束时间
        LocalDateTime endOfDay = LocalDateTime.now().with(LocalTime.MAX);
        // 构建查询条件，查询finish_time在当天的销售记录，并统计总销售价格
        Map<String, Object> result = cashTradeMapper.selectTotalSellPrice(startOfDay, endOfDay, type);
        // 解析查询结果
        BigDecimal totalSalesToday =  result != null ? (BigDecimal) result.get("order_total") : BigDecimal.ZERO;
        return totalSalesToday;
    }

}
