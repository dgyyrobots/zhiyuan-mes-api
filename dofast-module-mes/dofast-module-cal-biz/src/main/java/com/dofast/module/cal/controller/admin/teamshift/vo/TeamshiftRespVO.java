package com.dofast.module.cal.controller.admin.teamshift.vo;

import java.time.LocalDate;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 班组排班 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class TeamshiftRespVO extends TeamshiftBaseVO {

    @Schema(description = "流水号id", requiredMode = Schema.RequiredMode.REQUIRED, example = "24076")
    private Long id;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDateTime createTime;

}
