package com.dofast.module.hr.dal.mysql.tradecommission;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;
import com.dofast.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.dofast.framework.mybatis.core.mapper.BaseMapperX;
import com.dofast.module.hr.dal.dataobject.tradecommission.TradecommissionDO;
import org.apache.ibatis.annotations.Mapper;
import com.dofast.module.hr.controller.admin.tradecommission.vo.*;

/**
 * 工资提成 Mapper
 *
 * @author 惠智造
 */
@Mapper
public interface TradecommissionMapper extends BaseMapperX<TradecommissionDO> {

    default PageResult<TradecommissionDO> selectPage(TradecommissionPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<TradecommissionDO>()
                .eqIfPresent(TradecommissionDO::getType, reqVO.getType())
                .eqIfPresent(TradecommissionDO::getSignType, reqVO.getSignType())
                .eqIfPresent(TradecommissionDO::getSaleType, reqVO.getSaleType())
                .eqIfPresent(TradecommissionDO::getTrade, reqVO.getTrade())
                .eqIfPresent(TradecommissionDO::getContract, reqVO.getContract())
                .eqIfPresent(TradecommissionDO::getAccount, reqVO.getAccount())
                .eqIfPresent(TradecommissionDO::getContribution, reqVO.getContribution())
                .eqIfPresent(TradecommissionDO::getRate, reqVO.getRate())
                .eqIfPresent(TradecommissionDO::getAmount, reqVO.getAmount())
                .eqIfPresent(TradecommissionDO::getDesc, reqVO.getDesc())
                .betweenIfPresent(TradecommissionDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(TradecommissionDO::getId));
    }

    default List<TradecommissionDO> selectList(TradecommissionExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<TradecommissionDO>()
                .eqIfPresent(TradecommissionDO::getType, reqVO.getType())
                .eqIfPresent(TradecommissionDO::getSignType, reqVO.getSignType())
                .eqIfPresent(TradecommissionDO::getSaleType, reqVO.getSaleType())
                .eqIfPresent(TradecommissionDO::getTrade, reqVO.getTrade())
                .eqIfPresent(TradecommissionDO::getContract, reqVO.getContract())
                .eqIfPresent(TradecommissionDO::getAccount, reqVO.getAccount())
                .eqIfPresent(TradecommissionDO::getContribution, reqVO.getContribution())
                .eqIfPresent(TradecommissionDO::getRate, reqVO.getRate())
                .eqIfPresent(TradecommissionDO::getAmount, reqVO.getAmount())
                .eqIfPresent(TradecommissionDO::getDesc, reqVO.getDesc())
                .betweenIfPresent(TradecommissionDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(TradecommissionDO::getId));
    }

}
