package com.dofast.module.trade.controller.admin.calculate.calculatecategory.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Schema(description = "管理后台 - 计价分类更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CalculateCategoryUpdateReqVO extends CalculateCategoryBaseVO {

    @Schema(description = "ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "32128")
    @NotNull(message = "ID不能为空")
    private Integer id;

}
