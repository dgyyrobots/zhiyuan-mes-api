package com.dofast.module.statistics.service.pay;

import com.dofast.module.pay.enums.member.PayWalletBizTypeEnum;
import com.dofast.module.pay.enums.order.PayOrderStatusEnum;
import com.dofast.module.pay.enums.refund.PayRefundStatusEnum;
import com.dofast.module.statistics.dal.mysql.pay.PayWalletStatisticsMapper;
import com.dofast.module.statistics.service.pay.bo.RechargeSummaryRespBO;
import com.dofast.module.statistics.service.trade.bo.WalletSummaryRespBO;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 * 钱包的统计 Service 实现类
 *
 * @author owen
 */
@Service
@Validated
public class PayWalletStatisticsServiceImpl implements PayWalletStatisticsService {

    @Resource
    private PayWalletStatisticsMapper payWalletStatisticsMapper;

    @Override
    public WalletSummaryRespBO getWalletSummary(LocalDateTime beginTime, LocalDateTime endTime) {
        WalletSummaryRespBO paySummary = payWalletStatisticsMapper.selectRechargeSummaryByPayTimeBetween(
                beginTime, endTime, true);
        WalletSummaryRespBO refundSummary = payWalletStatisticsMapper.selectRechargeSummaryByRefundTimeBetween(
                beginTime, endTime, PayRefundStatusEnum.SUCCESS.getStatus());
        Integer walletPayPrice = payWalletStatisticsMapper.selectPriceSummaryByBizTypeAndCreateTimeBetween(
                beginTime, endTime, PayWalletBizTypeEnum.PAYMENT.getType());
        // 拼接
        paySummary.setWalletPayPrice(walletPayPrice)
                .setRechargeRefundCount(refundSummary.getRechargeRefundCount())
                .setRechargeRefundPrice(refundSummary.getRechargeRefundPrice());
        return paySummary;
    }

    @Override
    public RechargeSummaryRespBO getUserRechargeSummary(LocalDateTime beginTime, LocalDateTime endTime) {
        return payWalletStatisticsMapper.selectRechargeSummaryGroupByWalletId(beginTime, endTime, true);
    }

    @Override
    public Integer getRechargePriceSummary() {
        return payWalletStatisticsMapper.selectRechargePriceSummary(PayOrderStatusEnum.SUCCESS.getStatus());
    }

}
