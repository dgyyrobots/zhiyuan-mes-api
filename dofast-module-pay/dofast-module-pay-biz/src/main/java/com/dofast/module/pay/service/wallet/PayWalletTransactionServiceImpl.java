package com.dofast.module.pay.service.wallet;

import com.dofast.framework.common.pojo.PageResult;


import com.dofast.module.pay.controller.admin.wallet.vo.transaction.PayWalletTransactionPageReqVO;



import com.dofast.module.pay.controller.app.wallet.vo.transaction.AppPayWalletTransactionPageReqVO;
import com.dofast.module.pay.convert.wallet.PayWalletTransactionConvert;
import com.dofast.module.pay.dal.dataobject.wallet.PayWalletDO;
import com.dofast.module.pay.dal.dataobject.wallet.PayWalletTransactionDO;
import com.dofast.module.pay.dal.mysql.wallet.PayWalletTransactionMapper;
import com.dofast.module.pay.dal.redis.no.PayNoRedisDAO;
import com.dofast.module.pay.enums.member.PayWalletBizTypeEnum;
import com.dofast.module.pay.service.wallet.bo.WalletTransactionCreateReqBO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

 

import javax.annotation.Resource;
import java.time.LocalDateTime;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;



import javax.annotation.Resource;
import java.time.LocalDateTime;


/**
 * 钱包流水 Service 实现类
 *
 * @author jason
 */
@Service
@Slf4j


@Validated



public class PayWalletTransactionServiceImpl implements PayWalletTransactionService {

    /**
     * 钱包流水的 no 前缀
     */
    private static final String WALLET_NO_PREFIX = "W";

    @Resource
    private PayWalletService payWalletService;
    @Resource
    private PayWalletTransactionMapper payWalletTransactionMapper;
    @Resource
    private PayNoRedisDAO noRedisDAO;

    @Override
    public PageResult<PayWalletTransactionDO> getWalletTransactionPage(Long userId, Integer userType,
                                                                       AppPayWalletTransactionPageReqVO pageVO) {
        PayWalletDO wallet = payWalletService.getOrCreateWallet(userId, userType);


        return payWalletTransactionMapper.selectPage(wallet.getId(), pageVO.getType(), pageVO);
    }

    @Override
    public PageResult<PayWalletTransactionDO> getWalletTransactionPage(PayWalletTransactionPageReqVO pageVO) {
        return payWalletTransactionMapper.selectPage(pageVO.getWalletId(), null, pageVO);
    }

    @Override
    public PayWalletTransactionDO createWalletTransaction(WalletTransactionCreateReqBO bo) {
        PayWalletTransactionDO transaction = PayWalletTransactionConvert.INSTANCE.convert(bo)
                .setNo(noRedisDAO.generate(WALLET_NO_PREFIX));
        payWalletTransactionMapper.insert(transaction);
        return transaction;
    }

    @Override
    public PayWalletTransactionDO getWalletTransactionByNo(String no) {
        return payWalletTransactionMapper.selectByNo(no);
    }

    @Override
    public PayWalletTransactionDO getWalletTransaction(String bizId, PayWalletBizTypeEnum type) {
        return payWalletTransactionMapper.selectByBiz(bizId, type.getType());
    }


 


    @Override
    public Integer getPriceSummary(PayWalletBizTypeEnum bizType, LocalDateTime beginTime, LocalDateTime endTime) {
        return payWalletTransactionMapper.selectSummaryByBizTypeAndCreateTimeBetween(bizType.getType(), beginTime, endTime);
    }

}
