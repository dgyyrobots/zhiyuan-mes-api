package com.dofast.module.pay.dal.mysql.wallet;


 


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import com.dofast.framework.mybatis.core.mapper.BaseMapperX;
import com.dofast.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.dofast.module.pay.api.wallet.dto.WalletSummaryRespDTO;
import com.dofast.module.pay.dal.dataobject.wallet.PayWalletRechargeDO;
import com.dofast.module.pay.enums.refund.PayRefundStatusEnum;
import com.github.yulichang.toolkit.MPJWrappers;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDateTime;

@Mapper
public interface PayWalletRechargeMapper extends BaseMapperX<PayWalletRechargeDO> {


    default  int updateByIdAndPaid(Long id, boolean wherePayStatus, PayWalletRechargeDO updateObj){
        return update(updateObj, new LambdaQueryWrapperX<PayWalletRechargeDO>()
                .eq(PayWalletRechargeDO::getId, id).eq(PayWalletRechargeDO::getPayStatus, wherePayStatus));
    }


    default  int updateByIdAndRefunded(Long id, Integer whereRefundStatus, PayWalletRechargeDO updateObj){
        return update(updateObj, new LambdaQueryWrapperX<PayWalletRechargeDO>()
                .eq(PayWalletRechargeDO::getId, id).eq(PayWalletRechargeDO::getRefundStatus, whereRefundStatus));
    }


    default WalletSummaryRespDTO selectRechargeSummaryByPayTimeBetween(LocalDateTime beginTime, LocalDateTime endTime) {
        return BeanUtil.copyProperties(CollUtil.get(selectMaps(MPJWrappers.<PayWalletRechargeDO>lambdaJoin()
                        .selectCount(PayWalletRechargeDO::getId, WalletSummaryRespDTO::getRechargePayCount)
                        .selectSum(PayWalletRechargeDO::getPayPrice, WalletSummaryRespDTO::getRechargePayPrice)
                        .eq(PayWalletRechargeDO::getPayStatus, true)
                        .between(PayWalletRechargeDO::getPayTime, beginTime, endTime)), 0),
                WalletSummaryRespDTO.class);
    }

    default WalletSummaryRespDTO selectRechargeSummaryByRefundTimeBetween(LocalDateTime beginTime, LocalDateTime endTime) {
        return BeanUtil.copyProperties(CollUtil.get(selectMaps(MPJWrappers.<PayWalletRechargeDO>lambdaJoin()
                        .selectCount(PayWalletRechargeDO::getId, WalletSummaryRespDTO::getRechargeRefundCount)
                        .selectSum(PayWalletRechargeDO::getRefundPayPrice, WalletSummaryRespDTO::getRechargeRefundPrice)
                        .eq(PayWalletRechargeDO::getRefundStatus, PayRefundStatusEnum.SUCCESS.getStatus())
                        .between(PayWalletRechargeDO::getRefundTime, beginTime, endTime)), 0),
                WalletSummaryRespDTO.class);
    }
}





