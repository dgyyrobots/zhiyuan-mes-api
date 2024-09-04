package com.dofast.module.finance.dal.mysql.cash;

import java.time.LocalDateTime;
import java.util.*;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dofast.framework.common.pojo.PageResult;
import com.dofast.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.dofast.framework.mybatis.core.mapper.BaseMapperX;
import com.dofast.module.finance.controller.admin.bpm.cashTrade.vo.CashTradeBpmPageReqVO;
import com.dofast.module.finance.dal.dataobject.cash.CashTradeDO;
import org.apache.ibatis.annotations.Mapper;
import com.dofast.module.finance.controller.admin.cash.vo.*;

/**
 * 财务流水 Mapper
 *
 * @author 惠智造
 */
@Mapper
public interface CashTradeMapper extends BaseMapperX<CashTradeDO> {

    default PageResult<CashTradeDO> selectPage(CashTradePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<CashTradeDO>()
                .eqIfPresent(CashTradeDO::getDepositor, reqVO.getDepositor())
                .eqIfPresent(CashTradeDO::getParent, reqVO.getParent())
                .eqIfPresent(CashTradeDO::getProduct, reqVO.getProduct())
                .eqIfPresent(CashTradeDO::getTrader, reqVO.getTrader())
                .eqIfPresent(CashTradeDO::getOrderId, reqVO.getOrderId())
                .eqIfPresent(CashTradeDO::getContract, reqVO.getContract())
                .eqIfPresent(CashTradeDO::getRelatedType, reqVO.getRelatedType())
                .eqIfPresent(CashTradeDO::getRelatedId, reqVO.getRelatedId())
                .eqIfPresent(CashTradeDO::getProject, reqVO.getProject())
                .eqIfPresent(CashTradeDO::getInvestId, reqVO.getInvestId())
                .eqIfPresent(CashTradeDO::getLoanId, reqVO.getLoanId())
                .eqIfPresent(CashTradeDO::getTransferId, reqVO.getTransferId())
                .eqIfPresent(CashTradeDO::getDept, reqVO.getDept())
                .eqIfPresent(CashTradeDO::getType, reqVO.getType())
                .eqIfPresent(CashTradeDO::getMoney, reqVO.getMoney())
                .eqIfPresent(CashTradeDO::getExchangeRate, reqVO.getExchangeRate())
                .eqIfPresent(CashTradeDO::getCurrency, reqVO.getCurrency())
                .betweenIfPresent(CashTradeDO::getDate, reqVO.getDate())
                .betweenIfPresent(CashTradeDO::getTime, reqVO.getTime())
                .eqIfPresent(CashTradeDO::getDeadline, reqVO.getDeadline())
                .eqIfPresent(CashTradeDO::getHandlers, reqVO.getHandlers())
                .eqIfPresent(CashTradeDO::getCategory, reqVO.getCategory())
                .betweenIfPresent(CashTradeDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(CashTradeDO::getId));
    }

    default List<CashTradeDO> selectList(CashTradeExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<CashTradeDO>()
                .eqIfPresent(CashTradeDO::getDepositor, reqVO.getDepositor())
                .eqIfPresent(CashTradeDO::getParent, reqVO.getParent())
                .eqIfPresent(CashTradeDO::getProduct, reqVO.getProduct())
                .eqIfPresent(CashTradeDO::getTrader, reqVO.getTrader())
                .eqIfPresent(CashTradeDO::getOrderId, reqVO.getOrderId())
                .eqIfPresent(CashTradeDO::getContract, reqVO.getContract())
                .eqIfPresent(CashTradeDO::getRelatedType, reqVO.getRelatedType())
                .eqIfPresent(CashTradeDO::getRelatedId, reqVO.getRelatedId())
                .eqIfPresent(CashTradeDO::getProject, reqVO.getProject())
                .eqIfPresent(CashTradeDO::getInvestId, reqVO.getInvestId())
                .eqIfPresent(CashTradeDO::getLoanId, reqVO.getLoanId())
                .eqIfPresent(CashTradeDO::getTransferId, reqVO.getTransferId())
                .eqIfPresent(CashTradeDO::getDept, reqVO.getDept())
                .eqIfPresent(CashTradeDO::getType, reqVO.getType())
                .eqIfPresent(CashTradeDO::getMoney, reqVO.getMoney())
                .eqIfPresent(CashTradeDO::getExchangeRate, reqVO.getExchangeRate())
                .eqIfPresent(CashTradeDO::getCurrency, reqVO.getCurrency())
                .betweenIfPresent(CashTradeDO::getDate, reqVO.getDate())
                .betweenIfPresent(CashTradeDO::getTime, reqVO.getTime())
                .eqIfPresent(CashTradeDO::getDeadline, reqVO.getDeadline())
                .eqIfPresent(CashTradeDO::getHandlers, reqVO.getHandlers())
                .eqIfPresent(CashTradeDO::getCategory, reqVO.getCategory())
                .betweenIfPresent(CashTradeDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(CashTradeDO::getId));
    }

    default Map<String, Object> selectTotalSellPrice(LocalDateTime start, LocalDateTime end, Integer type){
        QueryWrapper<CashTradeDO> wrapper = new QueryWrapper<>();
        wrapper.select("SUM(money) AS order_total")
                .between("create_time", start, end)
                .eq("type", type);
        Map<String, Object> result = selectMaps(wrapper).get(0);
        return result;
    }

    default PageResult<CashTradeDO> selectPage(CashTradeBpmPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<CashTradeDO>()
                .eqIfPresent(CashTradeDO::getDepositor, reqVO.getDepositor())
                .eqIfPresent(CashTradeDO::getParent, reqVO.getParent())
                .eqIfPresent(CashTradeDO::getProduct, reqVO.getProduct())
                .eqIfPresent(CashTradeDO::getTrader, reqVO.getTrader())
                .eqIfPresent(CashTradeDO::getOrderId, reqVO.getOrderId())
                .eqIfPresent(CashTradeDO::getContract, reqVO.getContract())
                .eqIfPresent(CashTradeDO::getRelatedType, reqVO.getRelatedType())
                .eqIfPresent(CashTradeDO::getRelatedId, reqVO.getRelatedId())
                .eqIfPresent(CashTradeDO::getProject, reqVO.getProject())
                .eqIfPresent(CashTradeDO::getInvestId, reqVO.getInvestId())
                .eqIfPresent(CashTradeDO::getLoanId, reqVO.getLoanId())
                .eqIfPresent(CashTradeDO::getTransferId, reqVO.getTransferId())
                .eqIfPresent(CashTradeDO::getDept, reqVO.getDept())
                .eqIfPresent(CashTradeDO::getType, reqVO.getType())
                .eqIfPresent(CashTradeDO::getMoney, reqVO.getMoney())
                .eqIfPresent(CashTradeDO::getExchangeRate, reqVO.getExchangeRate())
                .eqIfPresent(CashTradeDO::getCurrency, reqVO.getCurrency())
                .betweenIfPresent(CashTradeDO::getDate, reqVO.getDate())
                .betweenIfPresent(CashTradeDO::getTime, reqVO.getTime())
                .eqIfPresent(CashTradeDO::getDeadline, reqVO.getDeadline())
                .eqIfPresent(CashTradeDO::getHandlers, reqVO.getHandlers())
                .eqIfPresent(CashTradeDO::getCategory, reqVO.getCategory())
                .betweenIfPresent(CashTradeDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(CashTradeDO::getId));
    }
}
