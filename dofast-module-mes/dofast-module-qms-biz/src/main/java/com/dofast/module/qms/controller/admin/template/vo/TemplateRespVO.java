package com.dofast.module.qms.controller.admin.template.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 检测模板 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class TemplateRespVO extends TemplateBaseVO {

    @Schema(description = "检测模板ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "4294")
    private Long id;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

}
