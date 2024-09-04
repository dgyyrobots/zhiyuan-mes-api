package com.dofast.module.statistics.dal.mysql.trade;

import com.dofast.framework.mybatis.core.mapper.BaseMapperX;
import com.dofast.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.dofast.module.statistics.controller.admin.trade.vo.TradeTrendSummaryRespVO;
import com.dofast.module.statistics.dal.dataobject.trade.TradeStatisticsDO;
import com.dofast.module.statistics.service.trade.bo.TradeSummaryRespBO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 交易统计 Mapper
 *
 * @author owen
 */
@Mapper
public interface TradeStatisticsMapper extends BaseMapperX<TradeStatisticsDO> {

    TradeSummaryRespBO selectOrderCreateCountSumAndOrderPayPriceSumByTimeBetween(@Param("beginTime") LocalDateTime beginTime,
                                                                                 @Param("endTime") LocalDateTime endTime);

    TradeTrendSummaryRespVO selectVoByTimeBetween(@Param("beginTime") LocalDateTime beginTime,
                                                  @Param("endTime") LocalDateTime endTime);

    // TODO @芋艿：已经 review
    default List<TradeStatisticsDO> selectListByTimeBetween(LocalDateTime beginTime, LocalDateTime endTime) {
        return selectList(new LambdaQueryWrapperX<TradeStatisticsDO>()
                .between(TradeStatisticsDO::getTime, beginTime, endTime));
    }

    // TODO @芋艿：已经 review
    Integer selectExpensePriceByTimeBetween(@Param("beginTime") LocalDateTime beginTime,
                                            @Param("endTime") LocalDateTime endTime);

    // TODO @芋艿：已经 review
    default TradeStatisticsDO selectByTimeBetween(LocalDateTime beginTime, LocalDateTime endTime) {
        return selectOne(new LambdaQueryWrapperX<TradeStatisticsDO>()
                .between(TradeStatisticsDO::getTime, beginTime, endTime));
    }

}
