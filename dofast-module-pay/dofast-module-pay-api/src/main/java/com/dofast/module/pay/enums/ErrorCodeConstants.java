package com.dofast.module.pay.enums;

import com.dofast.framework.common.exception.ErrorCode;

/**
 * Pay 错误码 Core 枚举类
 *
 * pay 系统，使用 1-007-000-000 段
 */
public interface ErrorCodeConstants {





    // === APP 模块 1007000000 ===
    ErrorCode APP_NOT_FOUND = new ErrorCode(1007000000, "App 不存在");
    ErrorCode APP_IS_DISABLE = new ErrorCode(1007000002, "App 已经被禁用");
    ErrorCode APP_EXIST_ORDER_CANT_DELETE =  new ErrorCode(1007000003, "支付应用存在支付订单，无法删除");
    ErrorCode APP_EXIST_REFUND_CANT_DELETE =  new ErrorCode(1007000004, "支付应用存在退款订单，无法删除");

    // === CHANNEL 模块 1007001000 ===
    ErrorCode CHANNEL_NOT_FOUND = new ErrorCode(1007001000, "支付渠道的配置不存在");
    ErrorCode CHANNEL_IS_DISABLE = new ErrorCode(1007001001, "支付渠道已经禁用");
    ErrorCode CHANNEL_EXIST_SAME_CHANNEL_ERROR = new ErrorCode(1007001004, "已存在相同的渠道");

    // === ORDER 模块 1007002000 ===
    ErrorCode PAY_ORDER_NOT_FOUND = new ErrorCode(1007002000, "支付订单不存在");
    ErrorCode PAY_ORDER_STATUS_IS_NOT_WAITING = new ErrorCode(1007002001, "支付订单不处于待支付");
    ErrorCode PAY_ORDER_STATUS_IS_SUCCESS = new ErrorCode(1007002002, "订单已支付，请刷新页面");
    ErrorCode PAY_ORDER_IS_EXPIRED = new ErrorCode(1007002003, "支付订单已经过期");
    ErrorCode PAY_ORDER_SUBMIT_CHANNEL_ERROR = new ErrorCode(1007002004, "发起支付报错，错误码：{}，错误提示：{}");
    ErrorCode PAY_ORDER_REFUND_FAIL_STATUS_ERROR = new ErrorCode(1007002005, "支付订单退款失败，原因：状态不是已支付或已退款");

    // === ORDER 模块(拓展单) 1007003000 ===
    ErrorCode PAY_ORDER_EXTENSION_NOT_FOUND = new ErrorCode(1007003000, "支付交易拓展单不存在");
    ErrorCode PAY_ORDER_EXTENSION_STATUS_IS_NOT_WAITING = new ErrorCode(1007003001, "支付交易拓展单不处于待支付");
    ErrorCode PAY_ORDER_EXTENSION_IS_PAID = new ErrorCode(1007003002, "订单已支付，请等待支付结果");

    // === 支付模块(退款) 1007006000 ===
    ErrorCode REFUND_PRICE_EXCEED = new ErrorCode(1007006000, "退款金额超过订单可退款金额");
    ErrorCode REFUND_HAS_REFUNDING = new ErrorCode(1007006002, "已经有退款在处理中");
    ErrorCode REFUND_EXISTS = new ErrorCode(1007006003, "已经存在退款单");
    ErrorCode REFUND_NOT_FOUND = new ErrorCode(1007006004, "支付退款单不存在");
    ErrorCode REFUND_STATUS_IS_NOT_WAITING = new ErrorCode(1007006005, "支付退款单不处于待退款");

    // === 钱包模块 1007007000 ===
    ErrorCode WALLET_NOT_FOUND = new ErrorCode(1007007000, "用户钱包不存在");
    ErrorCode WALLET_BALANCE_NOT_ENOUGH = new ErrorCode(1007007001, "钱包余额不足");
    ErrorCode WALLET_TRANSACTION_NOT_FOUND = new ErrorCode(1007007002, "未找到对应的钱包交易");
    ErrorCode WALLET_REFUND_AMOUNT_ERROR = new ErrorCode(1007007003, "钱包退款金额不对");
    ErrorCode WALLET_REFUND_EXIST = new ErrorCode(1007007004, "已经存在钱包退款");
    ErrorCode WALLET_FREEZE_PRICE_NOT_ENOUGH = new ErrorCode(1007007005, "钱包冻结余额不足");

    // TODO @jason：把钱包充值，单独搞个错误码段哈；

    ErrorCode WALLET_RECHARGE_NOT_FOUND = new ErrorCode(1007007005, "钱包充值记录不存在");
    ErrorCode WALLET_RECHARGE_UPDATE_PAID_STATUS_NOT_UNPAID = new ErrorCode(1007007006, "钱包充值更新支付状态失败，钱包充值记录不是【未支付】状态");
    ErrorCode WALLET_RECHARGE_UPDATE_PAID_PAY_ORDER_ID_ERROR = new ErrorCode(1007007007, "钱包充值更新支付状态失败，支付单编号不匹配");
    ErrorCode WALLET_RECHARGE_UPDATE_PAID_PAY_ORDER_STATUS_NOT_SUCCESS = new ErrorCode(1007007008, "钱包充值更新支付状态失败，支付单状态不是【支付成功】状态");
    ErrorCode WALLET_RECHARGE_UPDATE_PAID_PAY_PRICE_NOT_MATCH = new ErrorCode(1007007009, "钱包充值更新支付状态失败，支付单金额不匹配");
    ErrorCode WALLET_RECHARGE_REFUND_FAIL_NOT_PAID = new ErrorCode(1007900010, "钱包发起退款失败，钱包充值订单未支付");
    ErrorCode WALLET_RECHARGE_REFUND_FAIL_REFUNDED = new ErrorCode(1007900011, "钱包发起退款失败，钱包充值订单已退款");
    ErrorCode WALLET_RECHARGE_REFUND_BALANCE_NOT_ENOUGH = new ErrorCode(1007900012, "钱包发起退款失败，钱包余额不足");
    ErrorCode WALLET_RECHARGE_REFUND_FAIL_REFUND_ORDER_ID_ERROR = new ErrorCode(1007900013, "钱包退款更新失败，钱包退款单编号不匹配");
    ErrorCode WALLET_RECHARGE_REFUND_FAIL_REFUND_NOT_FOUND = new ErrorCode(1007900014, "钱包退款更新失败，退款订单不存在");
    ErrorCode WALLET_RECHARGE_REFUND_FAIL_REFUND_PRICE_NOT_MATCH = new ErrorCode(1007900015, "钱包退款更新失败，退款单金额不匹配");
    // === 示例订单 1007900000 ===
    ErrorCode DEMO_ORDER_NOT_FOUND = new ErrorCode(1007900000, "示例订单不存在");
    ErrorCode DEMO_ORDER_UPDATE_PAID_STATUS_NOT_UNPAID = new ErrorCode(1007900001, "示例订单更新支付状态失败，订单不是【未支付】状态");
    ErrorCode DEMO_ORDER_UPDATE_PAID_FAIL_PAY_ORDER_ID_ERROR = new ErrorCode(1007900002, "示例订单更新支付状态失败，支付单编号不匹配");
    ErrorCode DEMO_ORDER_UPDATE_PAID_FAIL_PAY_ORDER_STATUS_NOT_SUCCESS = new ErrorCode(1007900003, "示例订单更新支付状态失败，支付单状态不是【支付成功】状态");
    ErrorCode DEMO_ORDER_UPDATE_PAID_FAIL_PAY_PRICE_NOT_MATCH = new ErrorCode(1007900004, "示例订单更新支付状态失败，支付单金额不匹配");
    ErrorCode DEMO_ORDER_REFUND_FAIL_NOT_PAID = new ErrorCode(1007900005, "发起退款失败，示例订单未支付");
    ErrorCode DEMO_ORDER_REFUND_FAIL_REFUNDED = new ErrorCode(1007900006, "发起退款失败，示例订单已退款");
    ErrorCode DEMO_ORDER_REFUND_FAIL_REFUND_NOT_FOUND = new ErrorCode(1007900007, "发起退款失败，退款订单不存在");
    ErrorCode DEMO_ORDER_REFUND_FAIL_REFUND_NOT_SUCCESS = new ErrorCode(1007900008, "发起退款失败，退款订单未退款成功");
    ErrorCode DEMO_ORDER_REFUND_FAIL_REFUND_ORDER_ID_ERROR = new ErrorCode(1007900009, "发起退款失败，退款单编号不匹配");
    ErrorCode DEMO_ORDER_REFUND_FAIL_REFUND_PRICE_NOT_MATCH = new ErrorCode(1007900010, "发起退款失败，退款单金额不匹配");
    /**
     * === APP 模块 1-007-000-000 ===
     */
    ErrorCode PAY_APP_NOT_FOUND = new ErrorCode(1007000000, "App 不存在");
    ErrorCode PAY_APP_IS_DISABLE = new ErrorCode(1007000002, "App 已经被禁用");
    ErrorCode PAY_APP_EXIST_TRANSACTION_ORDER_CANT_DELETE =  new ErrorCode(1007000003, "支付应用存在交易中的订单,无法删除");

    /**
     * === CHANNEL 模块 1-007-001-000 ===
     */
    ErrorCode PAY_CHANNEL_NOT_FOUND = new ErrorCode(1007001000, "支付渠道的配置不存在");
    ErrorCode PAY_CHANNEL_IS_DISABLE = new ErrorCode(1007001001, "支付渠道已经禁用");
    ErrorCode PAY_CHANNEL_CLIENT_NOT_FOUND = new ErrorCode(1007001002, "支付渠道的客户端不存在");
    ErrorCode CHANNEL_NOT_EXISTS = new ErrorCode(1007001003, "支付渠道不存在");
    ErrorCode CHANNEL_WECHAT_VERSION_2_MCH_KEY_IS_NULL = new ErrorCode(1007001006,"微信渠道v2版本中商户密钥不可为空");
    ErrorCode CHANNEL_WECHAT_VERSION_3_PRIVATE_KEY_IS_NULL = new ErrorCode(1007001007,"微信渠道v3版本apiclient_key.pem不可为空");
    ErrorCode CHANNEL_WECHAT_VERSION_3_CERT_KEY_IS_NULL = new ErrorCode(1007001008,"微信渠道v3版本中apiclient_cert.pem不可为空");
    ErrorCode PAY_CHANNEL_NOTIFY_VERIFY_FAILED = new ErrorCode(1007001009, "渠道通知校验失败");

    ErrorCode PAY_ORDER_STATUS_IS_NOT_SUCCESS = new ErrorCode(1007002002, "支付订单不处于已支付");
    ErrorCode PAY_ORDER_ERROR_USER = new ErrorCode(1007002003, "支付订单用户不正确");


    ErrorCode PAY_ORDER_EXTENSION_STATUS_IS_NOT_SUCCESS = new ErrorCode(1007003002, "支付订单不处于已支付");

    // === 支付模块(退款) 1-007-006-000 ===
    ErrorCode PAY_REFUND_AMOUNT_EXCEED = new ErrorCode(1007006000, "退款金额超过订单可退款金额");
    ErrorCode PAY_REFUND_ALL_REFUNDED = new ErrorCode(1007006001, "订单已经全额退款");
    ErrorCode PAY_REFUND_CHN_ORDER_NO_IS_NULL = new ErrorCode(1007006002, "该订单的渠道订单为空");
    ErrorCode PAY_REFUND_SUCCEED = new ErrorCode(1007006003, "已经退款成功");
    ErrorCode PAY_REFUND_NOT_FOUND = new ErrorCode(1007006004, "支付退款单不存在");


    // === 示例订单 1-007-900-000 ===
    ErrorCode PAY_DEMO_ORDER_NOT_FOUND = new ErrorCode(100790000, "示例订单不存在");
    ErrorCode PAY_DEMO_ORDER_UPDATE_PAID_STATUS_NOT_UNPAID = new ErrorCode(100790001, "示例订单更新支付状态失败，订单不是【未支付】状态");
    ErrorCode PAY_DEMO_ORDER_UPDATE_PAID_FAIL_PAY_ORDER_ID_ERROR = new ErrorCode(100790002, "示例订单更新支付状态失败，支付单编号不匹配");
    ErrorCode PAY_DEMO_ORDER_UPDATE_PAID_FAIL_PAY_ORDER_STATUS_NOT_SUCCESS = new ErrorCode(100790003, "示例订单更新支付状态失败，支付单状态不是【支付成功】状态");
    ErrorCode PAY_DEMO_ORDER_UPDATE_PAID_FAIL_PAY_PRICE_NOT_MATCH = new ErrorCode(100790004, "示例订单更新支付状态失败，支付单金额不匹配");
    ErrorCode PAY_DEMO_ORDER_REFUND_FAIL_NOT_PAID = new ErrorCode(100790005, "发起退款失败，示例订单未支付");
    ErrorCode PAY_DEMO_ORDER_REFUND_FAIL_REFUNDED = new ErrorCode(100790006, "发起退款失败，示例订单已退款");
    ErrorCode PAY_DEMO_ORDER_REFUND_FAIL_REFUND_NOT_FOUND = new ErrorCode(100790007, "发起退款失败，退款订单不存在");
    ErrorCode PAY_DEMO_ORDER_REFUND_FAIL_REFUND_NOT_SUCCESS = new ErrorCode(100790008, "发起退款失败，退款订单未退款成功");
    ErrorCode PAY_DEMO_ORDER_REFUND_FAIL_REFUND_ORDER_ID_ERROR = new ErrorCode(100790008, "发起退款失败，退款单编号不匹配");
    ErrorCode PAY_DEMO_ORDER_REFUND_FAIL_REFUND_PRICE_NOT_MATCH = new ErrorCode(100790004, "发起退款失败，退款单金额不匹配");


    /**
     * === 支付商户信息 1-007-004-000 ===
     */
    ErrorCode PAY_MERCHANT_NOT_EXISTS = new ErrorCode(1007004000, "支付商户信息不存在");
    ErrorCode PAY_MERCHANT_EXIST_APP_CANT_DELETE = new ErrorCode(1007004001, "支付商户存在支付应用,无法删除");



    // === 转账模块 1-007-009-000 ===
    ErrorCode PAY_TRANSFER_SUBMIT_CHANNEL_ERROR = new ErrorCode(1_007_009_000, "发起转账报错，错误码：{}，错误提示：{}");



    ErrorCode WALLET_RECHARGE_PACKAGE_NOT_FOUND = new ErrorCode(1_007_008_011, "钱包充值套餐不存在");
    ErrorCode WALLET_RECHARGE_PACKAGE_IS_DISABLE = new ErrorCode(1_007_008_012, "钱包充值套餐已禁用");
    ErrorCode WALLET_RECHARGE_PACKAGE_NAME_EXISTS = new ErrorCode(1_007_008_013, "钱包充值套餐名称已存在");
    ErrorCode PAY_TRANSFER_NOT_FOUND = new ErrorCode(1_007_009_001, "转账交易单不存在");
    ErrorCode PAY_TRANSFER_STATUS_IS_SUCCESS = new ErrorCode(1_007_009_002, "转账单已成功转账");
    ErrorCode PAY_TRANSFER_EXISTS = new ErrorCode(1_007_009_003, "已经存在转账单");
    ErrorCode PAY_MERCHANT_TRANSFER_EXISTS = new ErrorCode(1_007_009_004, "该笔业务的转账已经存在,请查询转账订单相关状态");
    ErrorCode PAY_TRANSFER_ALIPAY_LOGIN_ID_IS_EMPTY = new ErrorCode(1_007_009_001, "支付宝登录 ID 不能为空");
    ErrorCode PAY_TRANSFER_ALIPAY_ACCOUNT_NAME_IS_EMPTY = new ErrorCode(1_007_009_002, "支付宝账号名称不能为空");
    ErrorCode PAY_TRANSFER_TYPE_AND_CHANNEL_NOT_MATCH = new ErrorCode(1_007_009_008, "转账类型和转账渠道不匹配");


    ErrorCode PAY_TRANSFER_STATUS_IS_NOT_WAITING = new ErrorCode(1_007_009_005, "转账单不处于待转账");
    ErrorCode PAY_TRANSFER_STATUS_IS_NOT_PENDING = new ErrorCode(1_007_009_006, "转账单不处于待转账或转账中");




    /**
     * === 支付商户信息 1-007-004-000 ===
     */
    ErrorCode WALLET_RECHARGE_PACKAGE_AND_PRICE_IS_EMPTY = new ErrorCode(1_007_008_011, "充值金额和充钱套餐不能同时为空");
    ErrorCode PAY_TRANSFER_EXTENSION_NOT_FOUND = new ErrorCode(1_007_009_007, "转账交易拓展单不存在");
    ErrorCode PAY_TRANSFER_EXTENSION_STATUS_IS_NOT_PENDING = new ErrorCode(1_007_009_009, "转账拓展单不处于待转账或转账中");





}
