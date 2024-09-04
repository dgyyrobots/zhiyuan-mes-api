package com.dofast.module.trade.controller.admin.mixinorderitem.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Schema(description = "管理后台 - 销售的物料明细更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class MixinOrderItemUpdateReqVO extends MixinOrderItemBaseVO {

    @Schema(description = "销售的物料ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "818")
    @NotNull(message = "销售的物料ID不能为空")
    private Long id;

}
