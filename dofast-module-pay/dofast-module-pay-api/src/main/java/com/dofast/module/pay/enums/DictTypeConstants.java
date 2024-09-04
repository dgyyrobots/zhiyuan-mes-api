package com.dofast.module.pay.enums;

/**
 * Pay 字典类型的枚举类
 *
 * @author 芋道源码
 */
public interface DictTypeConstants {

    String CHANNEL_CODE = "pay_channel_code"; // 支付渠道编码

    String ORDER_STATUS = "pay_order_status"; // 支付渠道

    String REFUND_STATUS = "pay_order_status"; // 退款状态

    String NOTIFY_STATUS = "pay_notify_status"; // 回调状态


 


    String TASK_ASSIGN_RULE_TYPE = "bpm_task_assign_rule_type"; // 任务分配规则类型
    String TASK_ASSIGN_SCRIPT = "bpm_task_assign_script"; // 任务分配自定义脚本


    String ORDER_NOTIFY_STATUS = "pay_order_notify_status"; // 支付-订单-订单回调商户状态

    String ORDER_REFUND_STATUS = "pay_order_refund_status"; // 支付-订单-订单退款状态
    String REFUND_ORDER_STATUS = "pay_refund_order_status"; // 支付-退款订单-退款状态
    String REFUND_ORDER_TYPE = "pay_refund_order_type"; // 支付-退款订单-退款类别






}
