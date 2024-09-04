package com.dofast.module.iot.controller.admin.thingsmodeltemplate.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 物模型模板更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ThingsModelTemplateUpdateReqVO extends ThingsModelTemplateBaseVO {

    @Schema(description = "物模型ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "11833")
    @NotNull(message = "物模型ID不能为空")
    private Long id;

}
