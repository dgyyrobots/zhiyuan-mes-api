package com.dofast.module.trade.enums;

import com.dofast.framework.common.exception.ErrorCode;

/**
 * Trade 错误码枚举类
 * trade 系统，使用 1-011-000-000 段
 *
 * @author LeeYan9
 * @since 2022-08-26
 */
public interface ErrorCodeConstants {

    // === Order 模块 1-011-000-000 ===
    ErrorCode ORDER_ITEM_NOT_FOUND = new ErrorCode(1_011_000_010, "交易订单项不存在");
    ErrorCode ORDER_NOT_FOUND = new ErrorCode(1_011_000_011, "交易订单不存在");
    ErrorCode ORDER_ITEM_UPDATE_AFTER_SALE_STATUS_FAIL = new ErrorCode(1_011_000_012, "交易订单项更新售后状态失败，请重试");
    ErrorCode ORDER_UPDATE_PAID_STATUS_NOT_UNPAID = new ErrorCode(1_011_000_013, "交易订单更新支付状态失败，订单不是【未支付】状态");
    ErrorCode ORDER_UPDATE_PAID_FAIL_PAY_ORDER_ID_ERROR = new ErrorCode(1_011_000_014, "交易订单更新支付状态失败，支付单编号不匹配");
    ErrorCode ORDER_UPDATE_PAID_FAIL_PAY_ORDER_STATUS_NOT_SUCCESS = new ErrorCode(1_011_000_015, "交易订单更新支付状态失败，支付单状态不是【支付成功】状态");
    ErrorCode ORDER_UPDATE_PAID_FAIL_PAY_PRICE_NOT_MATCH = new ErrorCode(1_011_000_016, "交易订单更新支付状态失败，支付单金额不匹配");
    ErrorCode ORDER_DELIVERY_FAIL_STATUS_NOT_UNDELIVERED = new ErrorCode(1_011_000_017, "交易订单发货失败，订单不是【待发货】状态");
    ErrorCode ORDER_RECEIVE_FAIL_STATUS_NOT_DELIVERED = new ErrorCode(1_011_000_018, "交易订单收货失败，订单不是【待收货】状态");
    ErrorCode ORDER_COMMENT_FAIL_STATUS_NOT_COMPLETED = new ErrorCode(1_011_000_019, "创建交易订单项的评价失败，订单不是【已完成】状态");
    ErrorCode ORDER_COMMENT_STATUS_NOT_FALSE = new ErrorCode(1_011_000_020, "创建交易订单项的评价失败，订单已评价");
    ErrorCode ORDER_DELIVERY_FAIL_REFUND_STATUS_NOT_NONE = new ErrorCode(1_011_000_021, "交易订单发货失败，订单已退款或部分退款");
    ErrorCode ORDER_DELIVERY_FAIL_COMBINATION_RECORD_STATUS_NOT_SUCCESS = new ErrorCode(1_011_000_022, "交易订单发货失败，拼团未成功");
    ErrorCode ORDER_DELIVERY_FAIL_BARGAIN_RECORD_STATUS_NOT_SUCCESS = new ErrorCode(1_011_000_023, "交易订单发货失败，砍价未成功");
    ErrorCode ORDER_DELIVERY_FAIL_DELIVERY_TYPE_NOT_EXPRESS = new ErrorCode(1_011_000_024, "交易订单发货失败，发货类型不是快递");
    ErrorCode ORDER_CANCEL_FAIL_STATUS_NOT_UNPAID = new ErrorCode(1_011_000_025, "交易订单取消失败，订单不是【待支付】状态");
    ErrorCode ORDER_UPDATE_PRICE_FAIL_PAID = new ErrorCode(1_011_000_026, "支付订单调价失败，原因：支付订单已付款,不能调价");
    ErrorCode ORDER_UPDATE_PRICE_FAIL_ALREADY = new ErrorCode(1_011_000_027, "支付订单调价失败，原因：已经修改过价格");
    ErrorCode ORDER_UPDATE_PRICE_FAIL_PRICE_ERROR = new ErrorCode(1_011_000_028, "支付订单调价失败，原因：调整后支付价格不能小于 0.01 元");
    ErrorCode ORDER_DELETE_FAIL_STATUS_NOT_CANCEL = new ErrorCode(1_011_000_029, "交易订单删除失败，订单不是【已取消】状态");
    ErrorCode ORDER_RECEIVE_FAIL_DELIVERY_TYPE_NOT_PICK_UP = new ErrorCode(1_011_000_030, "交易订单自提失败，收货方式不是【用户自提】");

    ErrorCode ORDER_UPDATE_ADDRESS_FAIL_STATUS_NOT_DELIVERED = new ErrorCode(1_011_000_031, "交易订单修改收货地址失败，原因：订单不是【待发货】状态");
    ErrorCode ORDER_CREATE_FAIL_EXIST_UNPAID = new ErrorCode(1_011_000_032, "交易订单创建失败，原因：存在未付款订单");



    // === After Sale 模块 1-011-000-100 ===
    ErrorCode AFTER_SALE_NOT_FOUND = new ErrorCode(1_011_000_100, "售后单不存在");
    ErrorCode AFTER_SALE_CREATE_FAIL_REFUND_PRICE_ERROR = new ErrorCode(1_011_000_101, "申请退款金额错误");
    ErrorCode AFTER_SALE_CREATE_FAIL_ORDER_STATUS_CANCELED = new ErrorCode(1_011_000_102, "订单已关闭，无法申请售后");
    ErrorCode AFTER_SALE_CREATE_FAIL_ORDER_STATUS_NO_PAID = new ErrorCode(1_011_000_103, "订单未支付，无法申请售后");
    ErrorCode AFTER_SALE_CREATE_FAIL_ORDER_STATUS_NO_DELIVERED = new ErrorCode(1_011_000_104, "订单未发货，无法申请【退货退款】售后");
    ErrorCode AFTER_SALE_CREATE_FAIL_ORDER_ITEM_APPLIED = new ErrorCode(1_011_000_105, "订单项已申请售后，无法重复申请");
    ErrorCode AFTER_SALE_AUDIT_FAIL_STATUS_NOT_APPLY = new ErrorCode(1_011_000_106, "审批失败，售后状态不处于审批中");
    ErrorCode AFTER_SALE_UPDATE_STATUS_FAIL = new ErrorCode(1_011_000_107, "操作售后单失败，请刷新后重试");
    ErrorCode AFTER_SALE_DELIVERY_FAIL_STATUS_NOT_SELLER_AGREE = new ErrorCode(1_011_000_108, "退货失败，售后单状态不处于【待买家退货】");
    ErrorCode AFTER_SALE_CONFIRM_FAIL_STATUS_NOT_BUYER_DELIVERY = new ErrorCode(1_011_000_109, "确认收货失败，售后单状态不处于【待确认收货】");
    ErrorCode AFTER_SALE_REFUND_FAIL_STATUS_NOT_WAIT_REFUND = new ErrorCode(1_011_000_110, "退款失败，售后单状态不是【待退款】");
    ErrorCode AFTER_SALE_CANCEL_FAIL_STATUS_NOT_APPLY_OR_AGREE_OR_BUYER_DELIVERY =
            new ErrorCode(1_011_000_111, "取消售后单失败，售后单状态不是【待审核】或【卖家同意】或【商家待收货】");

    // === Cart 模块 1-011-002-000 ===
    ErrorCode CARD_ITEM_NOT_FOUND = new ErrorCode(1_011_002_000, "购物车项不存在");

    // === Price 相关 1-011-003-000 =====
    ErrorCode PRICE_CALCULATE_PAY_PRICE_ILLEGAL = new ErrorCode(1_011_003_000, "支付价格计算异常，原因：价格小于等于 0");
    ErrorCode PRICE_CALCULATE_DELIVERY_PRICE_TEMPLATE_NOT_FOUND = new ErrorCode(1_011_003_002, "计算快递运费异常，找不到对应的运费模板");
    ErrorCode PRICE_CALCULATE_COUPON_NOT_MATCH_NORMAL_ORDER = new ErrorCode(1_011_003_004, "参与秒杀、拼团、砍价的营销商品，无法使用优惠劵");
    ErrorCode PRICE_CALCULATE_SECKILL_TOTAL_LIMIT_COUNT = new ErrorCode(1_011_003_005, "参与秒杀的商品，超过了秒杀总限购数量");

    // === 物流 Express 模块 1-011-004-000 ===
    ErrorCode EXPRESS_NOT_EXISTS = new ErrorCode(1_011_004_000, "快递公司不存在");
    ErrorCode EXPRESS_CODE_DUPLICATE = new ErrorCode(1_011_004_001, "已经存在该编码的快递公司");
    ErrorCode EXPRESS_CLIENT_NOT_PROVIDE = new ErrorCode(1_011_004_002, "需要接入快递服务商，比如【快递100】");
    ErrorCode EXPRESS_STATUS_NOT_ENABLE = new ErrorCode(1_011_004_003, "快递公司未启用");

    ErrorCode EXPRESS_API_QUERY_ERROR = new ErrorCode(1_011_004_101, "快递查询接口异常");
    ErrorCode EXPRESS_API_QUERY_FAILED = new ErrorCode(1_011_004_102, "快递查询返回失败，原因：{}");

    // === 物流 Template 模块 1-011-005-000 ===
    ErrorCode EXPRESS_TEMPLATE_NAME_DUPLICATE = new ErrorCode(1_011_005_000, "已经存在该运费模板名");
    ErrorCode EXPRESS_TEMPLATE_NOT_EXISTS = new ErrorCode(1_011_005_001, "运费模板不存在");

    // ===  物流 PICK_UP 模块 1-011-006-000 ===
    ErrorCode PICK_UP_STORE_NOT_EXISTS = new ErrorCode(1_011_006_000, "自提门店不存在");

    // === 分销用户 模块 1-011-007-000 ===
    ErrorCode BROKERAGE_USER_NOT_EXISTS = new ErrorCode(1_011_007_000, "分销用户不存在");
    ErrorCode BROKERAGE_USER_FROZEN_PRICE_NOT_ENOUGH = new ErrorCode(1_011_007_001, "用户冻结佣金({})数量不足");
    ErrorCode BROKERAGE_BIND_SELF = new ErrorCode(1_011_007_002, "不能绑定自己");
    ErrorCode BROKERAGE_BIND_USER_NOT_ENABLED = new ErrorCode(1_011_007_003, "绑定用户没有推广资格");
    ErrorCode BROKERAGE_BIND_CONDITION_ADMIN = new ErrorCode(1_011_007_004, "仅可在后台绑定推广员");
    ErrorCode BROKERAGE_BIND_MODE_REGISTER = new ErrorCode(1_011_007_005, "只有在注册时可以绑定");
    ErrorCode BROKERAGE_BIND_OVERRIDE = new ErrorCode(1_011_007_006, "已绑定了推广人");
    ErrorCode BROKERAGE_BIND_LOOP = new ErrorCode(1_011_007_007, "下级不能绑定自己的上级");
    ErrorCode BROKERAGE_USER_LEVEL_NOT_SUPPORT = new ErrorCode(1_011_007_008, "目前只支持 level 小于等于 2");

    // === 分销提现 模块 1-011-008-000 ===
    ErrorCode BROKERAGE_WITHDRAW_NOT_EXISTS = new ErrorCode(1_011_008_000, "佣金提现记录不存在");
    ErrorCode BROKERAGE_WITHDRAW_STATUS_NOT_AUDITING = new ErrorCode(1_011_008_001, "佣金提现记录状态不是审核中");
    ErrorCode BROKERAGE_WITHDRAW_MIN_PRICE = new ErrorCode(1_011_008_002, "提现金额不能低于 {} 元");
    ErrorCode BROKERAGE_WITHDRAW_USER_BALANCE_NOT_ENOUGH = new ErrorCode(1_011_008_003, "您当前最多可提现 {} 元");





    // ===  Order 模块 1-011-000-000 ===
    ErrorCode ORDER_CREATE_SKU_NOT_FOUND = new ErrorCode(1011000001, "商品 SKU 不存在");
    ErrorCode ORDER_CREATE_SPU_NOT_SALE = new ErrorCode(1011000002, "商品 SPU 不可售卖");
    ErrorCode ORDER_CREATE_SKU_NOT_SALE = new ErrorCode(1011000003, "商品 SKU 不可售卖");
    ErrorCode ORDER_CREATE_SKU_STOCK_NOT_ENOUGH = new ErrorCode(1011000004, "商品 SKU 库存不足");
    ErrorCode ORDER_CREATE_SPU_NOT_FOUND = new ErrorCode(1011000005, "商品 SPU 不可售卖");
    ErrorCode ORDER_CREATE_ADDRESS_NOT_FOUND = new ErrorCode(1011000006, "收货地址不存在");

    ErrorCode ORDER_CHANGE_ADDRESS_FAIL_STATUS_NOT_PASS = new ErrorCode(1011000019, "交易订单修改发货地址失败，订单不是【待发货】【待付款】状态");

    ErrorCode ORDER_UPDATE_ADDRESS_STATUS_NOT_DELIVERY = new ErrorCode(1011000020, "交易订单修改发货地址失败");
    ErrorCode ORDER_UPDATE_ADDRESS_STATUS_NOT_UNPAID = new ErrorCode(1011000021, "交易订单非未支付无法调整价格");

    ErrorCode CASH_TRADE_FAIL=new ErrorCode(1011000022,"入账失败");

    ErrorCode CHANNEL_ORDER_IS_NULL=new ErrorCode(1012000022,"渠道订单号为空");


    ErrorCode AFTER_SALE_CANCEL_FAIL_STATUS_NOT_APPLY_OR_AGREE = new ErrorCode(1011000111, "取消售后单失败，售后单状态不是【待审核】或【卖家同意】");


    // ===  After Sale 模块 1-011-000-000 ===


    ErrorCode CANNOT_CLOSE_ORDER = new ErrorCode(1011000112, "无法关闭订单，可先与卖家协商或申请售后");
    ErrorCode ORDER_HAS_CLOSED = new ErrorCode(1011000113, "订单已关闭无法重复关闭");
    ErrorCode ORDER_SHIPPED_FAIL_HAS_CLOSED = new ErrorCode(1011000114, "订单已关闭无法确认收货");
    ErrorCode ORDER_SHIPPED_FAIL_HAS_CANCELED = new ErrorCode(1011000115, "订单已取消无法确认收货");
    ErrorCode ORDER_SHIPPED_FAIL = new ErrorCode(1011000116, "请先发货");
    ErrorCode ORDER_NO_WAY_PAY = new ErrorCode(1011000117, "没有支付方式，请先创建支付应用");



    // === 计价类型 1025001000 ===
    ErrorCode CALCULATE_TYPE_NOT_EXISTS = new ErrorCode(1025001000, "计价类型不存在");

    // === 计价记录 1025002000 ===
    ErrorCode CALCULATE_RECORD_NOT_EXISTS = new ErrorCode(1025002000, "计价记录不存在");

    // === 采集任务 1027001000 ===
    ErrorCode ORDER_COLLECTION_NOT_EXISTS = new ErrorCode(1027001000, "采集任务不存在");

    // === 采集任务类型 1027002000 ===
    ErrorCode ORDER_COLLECTION_TYPE_NOT_EXISTS = new ErrorCode(1027002000, "采集任务类型不存在");

    // === 电子面单存储数据 1027003000 ===
    ErrorCode ELECTRONICSHEET_PACKAGE_NOT_EXISTS = new ErrorCode(1027003000, "电子面单数据不存在");

    // === 销售订单 1032000000 ===
    ErrorCode MIXIN_ORDER_NOT_EXISTS = new ErrorCode(1032001000, "销售订单不存在");
    ErrorCode TRADE_ORDER_BIND_MIXIN_ORDER_ERROR = new ErrorCode(1032001001, "销售订单和订单绑定失败,请检查订单.");

    // === 计价分类 1027004000 ===
    ErrorCode CALCULATE_CATEGORY_NOT_EXISTS = new ErrorCode(1027004000, "计价分类不存在");

    // === 计价模型 1027005000 ===
    ErrorCode CALCULATE_MODEL_NOT_EXISTS = new ErrorCode(1027005000, "计价模型不存在");

    // === 销售的物料明细 1027006000 ===
    ErrorCode MIXIN_ORDER_ITEM_NOT_EXISTS = new ErrorCode(1027006000, "销售的物料明细不存在");

    ErrorCode MIXIN_ORDER_IS_NOT_EXITS=new ErrorCode(1027006001,"销售订单不存在");

}
