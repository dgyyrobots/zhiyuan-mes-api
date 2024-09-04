package com.dofast.module.pay.app.dto;

import com.dofast.framework.common.enums.CommonStatusEnum;
import lombok.Data;

@Data
public class PayAppResDTO {
    /**
     * 应用编号，数据库自增
     */
    private Long id;
    /**
     * 应用名
     */
    private String name;
    /**
     * 状态
     *
     * 枚举 {@link CommonStatusEnum}
     */
    private Integer status;
    /**
     * 备注
     */
    private String remark;
    /**
     * 支付结果的回调地址
     */
    private String payNotifyUrl;
    /**
     * 退款结果的回调地址
     */
    private String refundNotifyUrl;

    /**
     * 商户编号
     *
     */
    private Long merchantId;

}
