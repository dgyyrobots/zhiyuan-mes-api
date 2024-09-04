package com.dofast.module.system.controller.admin.expresstemplateitem.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Schema(description = "管理后台 - 运费模板细节更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ExpressTemplateItemUpdateReqVO extends ExpressTemplateItemBaseVO {

    @Schema(description = "运费模板细节编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "17198")
    @NotNull(message = "运费模板细节编号不能为空")
    private Long id;

}
