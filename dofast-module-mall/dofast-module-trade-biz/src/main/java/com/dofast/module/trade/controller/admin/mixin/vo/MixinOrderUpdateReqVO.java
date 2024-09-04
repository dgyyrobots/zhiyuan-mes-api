package com.dofast.module.trade.controller.admin.mixin.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Schema(description = "管理后台 - 销售订单更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class MixinOrderUpdateReqVO extends MixinOrderBaseVO {

    @Schema(description = "销售订单ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "25322")
    @NotNull(message = "销售订单ID不能为空")
    private Long id;

    @Schema(description = "相关商品")
    private String goodsList;

    @Schema(description = "相关图片")
    private String pics;

}
