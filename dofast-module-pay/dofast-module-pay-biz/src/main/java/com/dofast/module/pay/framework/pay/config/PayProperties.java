package com.dofast.module.pay.framework.pay.config;

import lombok.Data;
import org.hibernate.validator.constraints.URL;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;

@ConfigurationProperties(prefix = "dofast.pay")
@Validated
@Data
public class PayProperties {

    private static final String ORDER_NO_PREFIX = "P";
    private static final String REFUND_NO_PREFIX = "R";

    /**
     * 支付回调地址
     *
     * 实际上，对应的 PayNotifyController 的 notifyOrder 方法的 URL
     *




     * 回调顺序：支付渠道（支付宝支付、微信支付） => yudao-module-pay 的 orderNotifyUrl 地址 => 业务的 PayAppDO.orderNotifyUrl 地址
     */
    /*@NotEmpty(message = "支付回调地址不能为空")
    @URL(message = "支付回调地址的格式必须是 URL")
    private String orderNotifyUrl;*/

    /**
     * 回调地址
     *
     * 实际上，对应的 PayNotifyController 的 notifyCallback 方法的 URL
     *
     * 注意，支付渠道统一回调到 payNotifyUrl 地址，由支付模块统一处理；然后，自己的支付模块，在回调 PayAppDO.payNotifyUrl 地址
     */
    @NotEmpty(message = "回调地址不能为空")
    @URL(message = "回调地址的格式必须是 URL")
    private String callbackUrl;

    /**

     * 回调顺序：支付渠道（支付宝支付、微信支付） => dofast-module-pay 的 orderNotifyUrl 地址 => 业务的 PayAppDO.orderNotifyUrl 地址
     */
    @NotEmpty(message = "支付回调地址不能为空")
    @URL(message = "支付回调地址的格式必须是 URL")
    private String returnUrl;


    /**


     * 退款回调地址
     *
     * 实际上，对应的 PayNotifyController 的 notifyRefund 方法的 URL
     *




     * 回调顺序：支付渠道（支付宝支付、微信支付） => yudao-module-pay 的 refundNotifyUrl 地址 => 业务的 PayAppDO.notifyRefundUrl 地址
     */
    /*@NotEmpty(message = "支付回调地址不能为空")
    @URL(message = "支付回调地址的格式必须是 URL")
    private String refundNotifyUrl;*/

    /**

     * 回调顺序：支付渠道（支付宝支付、微信支付） => dofast-module-pay 的 refundNotifyUrl 地址 => 业务的 PayAppDO.notifyRefundUrl 地址
     */
/*
    @NotEmpty(message = "支付回调地址不能为空")
    @URL(message = "支付回调地址的格式必须是 URL")
    private String refundNotifyUrl;
*/



    /**
     * 支付订单 no 的前缀
     */
    @NotEmpty(message = "支付订单 no 的前缀不能为空")
    private String orderNoPrefix = ORDER_NO_PREFIX;

    /**
     * 退款订单 no 的前缀
     */
    @NotEmpty(message = "退款订单 no 的前缀不能为空")
    private String refundNoPrefix = REFUND_NO_PREFIX;








}
