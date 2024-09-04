package com.dofast.module.system.controller.admin.expresstemplate.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Schema(description = "管理后台 - 运费模板更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ExpressTemplateUpdateReqVO extends ExpressTemplateBaseVO {

    @Schema(description = "运费模板编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "2858")
    @NotNull(message = "运费模板编号不能为空")
    private Long id;

}
