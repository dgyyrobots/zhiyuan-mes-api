package com.dofast.module.pay.service.wallet.bo;



import com.dofast.module.pay.enums.member.PayWalletBizTypeEnum;
import lombok.Data;

import com.dofast.framework.common.validation.InEnum;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;



import com.dofast.module.pay.enums.member.PayWalletBizTypeEnum;
import lombok.Data;


/**
 * 创建钱包流水 BO
 *
 * @author jason
 */
@Data
public class WalletTransactionCreateReqBO {



    // TODO @jason：bo 的话，最好加个参数校验哈；

    // TODO @jason：bo 的话，最好加个参数校验哈；


    /**
     * 钱包编号
     *
     */

    @NotNull(message = "钱包编号不能为空")



    private Long walletId;

    /**
     * 交易金额，单位分
     *
     * 正值表示余额增加，负值表示余额减少
     */


    @NotNull(message = "交易金额不能为空")



    private Integer price;

    /**
     * 交易后余额，单位分
     */


    @NotNull(message = "交易后余额不能为空")



    private Integer balance;

    /**
     * 关联业务分类
     *
     * 枚举 {@link PayWalletBizTypeEnum#getType()}
     */


    @NotNull(message = "关联业务分类不能为空")
    @InEnum(PayWalletBizTypeEnum.class)



    private Integer bizType;

    /**
     * 关联业务编号
     */


    @NotEmpty(message = "关联业务编号不能为空")



    private String bizId;

    /**
     * 流水说明
     */


    @NotEmpty(message = "流水说明不能为空")



    private String title;
}
