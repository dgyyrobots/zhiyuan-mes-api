package com.dofast.module.trade.controller.admin.order.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import liquibase.pro.packaged.S;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Schema(description = "管理后台 - 订单修改配送方式 Request VO")
@Data
public class TradeOrderUpdateDeliveryTypeReqVO {
    @Schema(description = "订单编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "订单ID不能为空")
    private Long id;

    @Schema(description = "配送类型",requiredMode = Schema.RequiredMode.REQUIRED,example = "1")
    @NotNull(message = "配送类型不能为空")
    private Integer deliveryType;

}
