package com.dofast.module.statistics.job.trade;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.ObjUtil;
import cn.hutool.core.util.StrUtil;
import com.dofast.framework.quartz.core.handler.JobHandler;
import com.dofast.framework.tenant.core.job.TenantJob;
import com.dofast.module.statistics.service.trade.TradeStatisticsService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

// TODO 芋艿：缺个 Job 的配置；等和 Product 一起配置
/**
 * 交易统计 Job
 *
 * @author owen
 */
@Component
public class TradeStatisticsJob implements JobHandler {

    @Resource
    private TradeStatisticsService tradeStatisticsService;

    /**
     * 执行交易统计任务
     *
     * @param param 要统计的天数，只能是正整数，1 代表昨日数据
     * @return 统计结果
     */
    @Override

    @TenantJob

//    @TenantJob

    public String execute(String param) {
        // 默认昨日
        param = ObjUtil.defaultIfBlank(param, "1");
        // 校验参数的合理性
        if (!NumberUtil.isInteger(param)) {
            throw new RuntimeException("交易统计任务的参数只能为是正整数");
        }
        Integer days = Convert.toInt(param, 0);
        if (days < 1) {
            throw new RuntimeException("交易统计任务的参数只能为是正整数");
        }
        String result = tradeStatisticsService.statisticsTrade(days);
        return StrUtil.format("交易统计:\n{}", result);
    }

}
