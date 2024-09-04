package com.dofast.module.trade.controller.admin.order.vo;

import lombok.Data;

import java.util.List;

@Data
public class TradeOrderListReqVO {

    private Long mixinOrderId;

    private List<Long> mixinOrderIds;

    private Integer status;

    private String orderNo;
}
