package com.dofast.module.pay.service.wallet;

import com.dofast.framework.common.pojo.PageResult;


import com.dofast.module.pay.controller.admin.wallet.vo.transaction.PayWalletTransactionPageReqVO;



import com.dofast.module.pay.controller.app.wallet.vo.transaction.AppPayWalletTransactionPageReqVO;
import com.dofast.module.pay.dal.dataobject.wallet.PayWalletTransactionDO;
import com.dofast.module.pay.enums.member.PayWalletBizTypeEnum;
import com.dofast.module.pay.service.wallet.bo.WalletTransactionCreateReqBO;

import javax.validation.Valid;

 
import java.time.LocalDateTime;



import java.time.LocalDateTime;


/**
 * 钱包余额流水 Service 接口
 *
 * @author jason
 */
public interface PayWalletTransactionService {

    /**
     * 查询钱包余额流水分页
     *
     * @param userId   用户编号
     * @param userType 用户类型
     * @param pageVO   分页查询参数
     */
    PageResult<PayWalletTransactionDO> getWalletTransactionPage(Long userId, Integer userType,
                                                                AppPayWalletTransactionPageReqVO pageVO);

    /**


     * 查询钱包余额流水分页
     *
     * @param pageVO   分页查询参数
     */
    PageResult<PayWalletTransactionDO> getWalletTransactionPage(PayWalletTransactionPageReqVO pageVO);

    /**



     * 新增钱包余额流水
     *
     * @param bo 创建钱包流水 bo
     * @return 新建的钱包 do
     */
    PayWalletTransactionDO createWalletTransaction(@Valid WalletTransactionCreateReqBO bo);

    /**
     * 根据 no，获取钱包余流水
     *
     * @param no 流水号
     */
    PayWalletTransactionDO getWalletTransactionByNo(String no);

    /**
     * 获取钱包流水

     * @param bizId 业务编号


     *
     * @param bizId  业务编号

     * @param type  业务类型
     * @return 钱包流水
     */
    PayWalletTransactionDO getWalletTransaction(String bizId, PayWalletBizTypeEnum type);


 



    /**
     * 获取支付金额合计
     *
     * @param bizType   业务类型
     * @param beginTime 开始时间
     * @param endTime   结束时间
     * @return 支付金额合计
     */
    Integer getPriceSummary(PayWalletBizTypeEnum bizType, LocalDateTime beginTime, LocalDateTime endTime);





}
