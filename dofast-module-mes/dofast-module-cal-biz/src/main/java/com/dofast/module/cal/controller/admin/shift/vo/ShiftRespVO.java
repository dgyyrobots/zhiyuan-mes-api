package com.dofast.module.cal.controller.admin.shift.vo;

import java.time.LocalDate;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 计划班次 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ShiftRespVO extends ShiftBaseVO {

    @Schema(description = "班次ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "4669")
    private Long id;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDateTime createTime;

}
