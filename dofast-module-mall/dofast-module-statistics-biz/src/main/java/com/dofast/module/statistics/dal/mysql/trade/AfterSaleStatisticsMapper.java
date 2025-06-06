package com.dofast.module.statistics.dal.mysql.trade;

import com.dofast.framework.mybatis.core.mapper.BaseMapperX;
import com.dofast.module.statistics.dal.dataobject.trade.TradeStatisticsDO;
import com.dofast.module.statistics.service.trade.bo.AfterSaleSummaryRespBO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;

/**
 * 售后订单的统计 Mapper
 *
 * @author owen
 */
@Mapper
public interface AfterSaleStatisticsMapper extends BaseMapperX<TradeStatisticsDO> {

    // TODO 芋艿：已 review
    AfterSaleSummaryRespBO selectSummaryByRefundTimeBetween(@Param("beginTime") LocalDateTime beginTime,
                                                            @Param("endTime") LocalDateTime endTime);

    // TODO 芋艿：已经 review
    Long selectCountByStatus(@Param("status") Integer status);

}
