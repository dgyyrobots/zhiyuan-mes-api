package com.dofast.module.cal.controller.admin.teammember.vo;

import java.time.LocalDate;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 班组成员 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class TeamMemberRespVO extends TeamMemberBaseVO {

    @Schema(description = "班组成员ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "3936")
    private Long id;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDateTime createTime;

}
