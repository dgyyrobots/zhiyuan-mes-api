package com.dofast.module.cal.controller.admin.planteam.vo;

import java.time.LocalDate;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 计划班组 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class PlanTeamRespVO extends PlanTeamBaseVO {

    @Schema(description = "流水号id", requiredMode = Schema.RequiredMode.REQUIRED, example = "31311")
    private Long id;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDateTime createTime;

}
