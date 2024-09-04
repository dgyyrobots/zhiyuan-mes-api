package com.dofast.module.iot.controller.admin.thingsmodeltemplate.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 物模型模板 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ThingsModelTemplateRespVO extends ThingsModelTemplateBaseVO {

    @Schema(description = "物模型ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "11833")
    private Long id;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDateTime createTime;

}
