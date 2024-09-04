package com.dofast.module.trade.api.mixinorder.dto;

import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 销售订单 DO
 *
 * @author 惠智造
 */
@Data
@ToString(callSuper = true)
public class MixinOrderDTO{

    /**
     * 销售订单ID
     */
    private Long id;
    /**
     * 销售单编码
     */
    private String saleNo;
    /**
     * 标题
     */
    private String title;
    /**
     * 标题
     */
    private String address;
    /**
     * 业务员
     */
    private Integer saler;
    /**
     * 业务员名称
     */
    private String salerName;
    /**
     * 结算方式
     */
    private Integer settlementMethod;
    /**
     * 预计交付时间
     */
    private LocalDateTime estimatedDeliveryTime;
    /**
     * 订单总金额
     */
    private BigDecimal price;
    /**
     * 相关商品
     */
    private String goodsList;
    /**
     * 相关图片
     */
    private String pics;
    /**
     * 订单状态
     */
    private String status;

}
