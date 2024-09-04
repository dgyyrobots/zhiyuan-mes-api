package com.dofast.module.channel.controller.admin.order.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Schema(description = "管理后台 - 店铺订单出库（京东厂送专用） Request VO")
@Data
@ToString(callSuper = true)
public class ShopOrderShippedReq {
    @Schema(description = "订单编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "123455")
    @NotNull(message = "订单编号不能为空")
    private String refOid;

    @Schema(description = "店铺编码", requiredMode = Schema.RequiredMode.REQUIRED, example = "1234")
    @NotNull(message = "店铺编码不能为空")
    private String posCode;
}
