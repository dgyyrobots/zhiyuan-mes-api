package com.dofast.module.qms.controller.admin.template.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 检测模板更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class TemplateUpdateReqVO extends TemplateBaseVO {

    @Schema(description = "检测模板ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "4294")
    @NotNull(message = "检测模板ID不能为空")
    private Long id;

}
