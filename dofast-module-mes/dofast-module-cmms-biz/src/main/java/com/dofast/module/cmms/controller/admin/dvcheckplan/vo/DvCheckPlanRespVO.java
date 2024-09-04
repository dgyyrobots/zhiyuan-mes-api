package com.dofast.module.cmms.controller.admin.dvcheckplan.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 设备点检保养计划头 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class DvCheckPlanRespVO extends DvCheckPlanBaseVO {

    @Schema(description = "计划ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "25512")
    private Long id;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

}
