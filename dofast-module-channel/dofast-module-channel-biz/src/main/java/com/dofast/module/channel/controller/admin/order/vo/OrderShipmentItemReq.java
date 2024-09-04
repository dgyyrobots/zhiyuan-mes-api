package com.dofast.module.channel.controller.admin.order.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Schema(description = "管理后台 - 店铺订单货品详情 Request VO")
@Data
@ToString(callSuper = true)
public class OrderShipmentItemReq {
    @Schema(description = "子订单id", requiredMode = Schema.RequiredMode.REQUIRED, example = "324242342342")
    @NotNull(message = "子订单id不能为空")
    private String refOlId;

    @Schema(description = "商品id", requiredMode = Schema.RequiredMode.REQUIRED, example = "")
    @NotNull(message = "商品id不能为空")
    private String refSkuId;

    @Schema(description = "数量", requiredMode = Schema.RequiredMode.REQUIRED, example = "5")
    @NotNull(message = "数量不能为空")
    private Integer num;
}
