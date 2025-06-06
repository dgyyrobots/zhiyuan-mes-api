package com.dofast.module.trade.api.brokerage;

import com.dofast.module.trade.service.brokerage.BrokerageRecordService;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 * 订单 API 接口实现类
 *
 * @author HUIHUI
 */
@Service
@Validated
public class TradeBrokerageApiImpl implements TradeBrokerageApi {

    @Resource
    private BrokerageRecordService brokerageRecordService;

    @Override
    public Integer getBrokerageSettlementPriceSummary(LocalDateTime beginTime, LocalDateTime endTime) {
        return brokerageRecordService.getBrokerageSettlementPriceSummary(beginTime, endTime);
    }

}
