package com.dofast.module.channel.controller.admin.order.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 所有订单统计分装类
 */
@Data
@ToString
@EqualsAndHashCode
@Schema(description = "管理后台 - 主订单列表 OrdedrNumber VO")
public class OrdedrNumberVO {
    @Schema(description = "今日订单数量统计")
    private Integer todayOrderCount;

    @Schema(description = "昨日订单数量统计")
    private Integer yestdayOrderCount;

    @Schema(description = "所有订单数量统计")
    private Integer orderCount;

    @Schema(description = "所有订单的售出金额")
    private Integer orderMoneyCount;
}
