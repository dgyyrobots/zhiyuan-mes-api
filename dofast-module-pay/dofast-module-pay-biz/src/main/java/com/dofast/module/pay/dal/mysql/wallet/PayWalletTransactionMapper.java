package com.dofast.module.pay.dal.mysql.wallet;




import com.dofast.framework.common.pojo.PageParam;



import com.dofast.framework.common.pojo.PageResult;
import com.dofast.framework.mybatis.core.mapper.BaseMapperX;
import com.dofast.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.dofast.module.pay.controller.app.wallet.vo.transaction.AppPayWalletTransactionPageReqVO;
import com.dofast.module.pay.dal.dataobject.wallet.PayWalletTransactionDO;

 


import com.github.yulichang.toolkit.MPJWrappers;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;



@Mapper
public interface PayWalletTransactionMapper extends BaseMapperX<PayWalletTransactionDO> {



    default PageResult<PayWalletTransactionDO> selectPage(Long walletId,
                                                          AppPayWalletTransactionPageReqVO pageReqVO) {
        LambdaQueryWrapperX<PayWalletTransactionDO> query = new LambdaQueryWrapperX<PayWalletTransactionDO>()
                .eq(PayWalletTransactionDO::getWalletId, walletId);
        if (Objects.equals(pageReqVO.getType(), AppPayWalletTransactionPageReqVO.TYPE_INCOME)) {
            query.gt(PayWalletTransactionDO::getPrice, 0);
        } else if (Objects.equals(pageReqVO.getType(), AppPayWalletTransactionPageReqVO.TYPE_EXPENSE)) {
            query.lt(PayWalletTransactionDO::getPrice, 0);
        }
        query.orderByDesc(PayWalletTransactionDO::getId);
        return selectPage(pageReqVO, query);
    }

    default PageResult<PayWalletTransactionDO> selectPage(Long walletId, Integer type,
                                                          PageParam pageParam) {
        LambdaQueryWrapperX<PayWalletTransactionDO> query = new LambdaQueryWrapperX<PayWalletTransactionDO>()
                .eqIfPresent(PayWalletTransactionDO::getWalletId, walletId);
        if (Objects.equals(type, AppPayWalletTransactionPageReqVO.TYPE_INCOME)) {
            query.gt(PayWalletTransactionDO::getPrice, 0);
        } else if (Objects.equals(type, AppPayWalletTransactionPageReqVO.TYPE_EXPENSE)) {
            query.lt(PayWalletTransactionDO::getPrice, 0);
        }
        query.orderByDesc(PayWalletTransactionDO::getId);
        return selectPage(pageParam, query);

    }



    default PayWalletTransactionDO selectByNo(String no) {
        return selectOne(PayWalletTransactionDO::getNo, no);
    }

    default PayWalletTransactionDO selectByBiz(String bizId, Integer bizType) {
        return selectOne(PayWalletTransactionDO::getBizId, bizId,
                PayWalletTransactionDO::getBizType, bizType);
    }


 


    default Integer selectSummaryByBizTypeAndCreateTimeBetween(Integer type, LocalDateTime beginTime, LocalDateTime endTime) {
        return Optional.ofNullable(selectOne(MPJWrappers.<PayWalletTransactionDO>lambdaJoin()
                        .selectSum(PayWalletTransactionDO::getPrice)
                        .eq(PayWalletTransactionDO::getBizType, type)
                        .between(PayWalletTransactionDO::getCreateTime, beginTime, endTime)))
                .map(PayWalletTransactionDO::getPrice)
                .orElse(0);
    }




}




