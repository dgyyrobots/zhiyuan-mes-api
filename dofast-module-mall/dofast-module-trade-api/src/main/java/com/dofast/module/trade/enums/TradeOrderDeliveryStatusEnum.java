package com.dofast.module.trade.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 交易订单 - 发货状态
 *
 * @author 芋道源码
 */
@RequiredArgsConstructor
@Getter
public enum TradeOrderDeliveryStatusEnum {

//    UNDELIVERED(0, "未发货"),

    UNPAID(0, "未发货"),
    DELIVERED(1, "已发货"),
    RECEIVED(2, "已收货"),
    NOT_SHIPPED(3, "NOT_SHIPPED"),
    PART_SHIPPED(4, "PART_SHIPPED"),
    SHIPPED(5, "SHIPPED"),
    COMPLETE(6, "COMPLETE");

    /**
     * 状态值
     */
    private final Integer status;
    /**
     * 状态名
     */
    private final String name;

}
