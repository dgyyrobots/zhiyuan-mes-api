package com.dofast.module.statistics.service.pay;

import com.dofast.module.statistics.service.pay.bo.RechargeSummaryRespBO;
import com.dofast.module.statistics.service.trade.bo.WalletSummaryRespBO;

import java.time.LocalDateTime;

/**
 * 钱包的统计 Service 接口
 *
 * @author owen
 */
public interface PayWalletStatisticsService {

    // TODO 芋艿：已经 review
    /**
     * 获取钱包统计
     *
     * @param beginTime 起始时间
     * @param endTime   截止时间
     * @return 钱包统计
     */
    WalletSummaryRespBO getWalletSummary(LocalDateTime beginTime, LocalDateTime endTime);

    // TODO 芋艿：已经 review
    /**
     * 获取钱包充值统计
     *
     * @param beginTime 起始时间
     * @param endTime   截止时间
     * @return 钱包充值统计
     */
    RechargeSummaryRespBO getUserRechargeSummary(LocalDateTime beginTime, LocalDateTime endTime);

    // TODO 芋艿：已经 review
    /**
     * 获取充值金额合计
     *
     * @return 充值金额合计
     */
    Integer getRechargePriceSummary();

}
