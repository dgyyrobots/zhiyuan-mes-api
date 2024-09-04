package com.dofast.module.tm.controller.admin.tool.vo;

import java.time.LocalDate;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 工装夹具清单 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ToolRespVO extends ToolBaseVO {

    @Schema(description = "工装夹具ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "27171")
    private Long id;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDateTime createTime;

}
