package com.dofast.module.purchase.enums;

import com.dofast.framework.common.exception.ErrorCode;

/**
 */
public interface ErrorCodeConstants {

    // === 采购商品明细 1030000000 ===
    ErrorCode GOODS_NOT_EXISTS = new ErrorCode(1030001000, "采购商品明细不存在");

    // === 采购入库单 1030002000 ===
    ErrorCode INVOICE_NOT_EXISTS = new ErrorCode(1030002000, "采购入库单不存在");

    // === 采购订单 1030003000 ===
    ErrorCode ORDER_NOT_EXISTS = new ErrorCode(1030003000, "采购订单不存在");

    // === 采购退货 1030004000 ===
    ErrorCode REFUND_NOT_EXISTS = new ErrorCode(1030004000, "采购退货不存在");


}
