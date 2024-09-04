package com.dofast.module.qms.controller.admin.templateindex.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 检测模板-检测项 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class TemplateIndexRespVO extends TemplateIndexBaseVO {

    @Schema(description = "记录ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "9719")
    private Long id;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

}
