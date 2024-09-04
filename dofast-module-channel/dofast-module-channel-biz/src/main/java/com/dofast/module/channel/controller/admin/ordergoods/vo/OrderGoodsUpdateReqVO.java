package com.dofast.module.channel.controller.admin.ordergoods.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Schema(description = "管理后台 - 子订单更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class OrderGoodsUpdateReqVO extends OrderGoodsBaseVO {

    @Schema(description = "订单商品", requiredMode = Schema.RequiredMode.REQUIRED, example = "5094")
    @NotNull(message = "订单商品不能为空")
    private Integer id;

}
