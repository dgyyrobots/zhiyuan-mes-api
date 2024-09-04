package com.dofast.module.channel.controller.admin.order.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Date;

/**
 * 用于日统计的数据封装
 */
@Schema(description = "管理后台 - 主订单日统计数据 OrderShopDayN VO")
@Data
@ToString(callSuper = true)
public class OrderShopDayNVO {
    @Schema(description = "日期", example = "2022-02-02")
    private String day;

    @Schema(description = "当天订单总数")
    private Integer orderCount;

    public OrderShopDayNVO(String day, Integer orderCount) {
        this.day = day;
        this.orderCount = orderCount;
    }
}
