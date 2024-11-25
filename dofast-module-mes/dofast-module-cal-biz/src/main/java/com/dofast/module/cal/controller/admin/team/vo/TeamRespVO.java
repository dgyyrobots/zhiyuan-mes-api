package com.dofast.module.cal.controller.admin.team.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 班组 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class TeamRespVO extends TeamBaseVO {

    @Schema(description = "班组ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "15224")
    private Long id;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDateTime createTime;

}
