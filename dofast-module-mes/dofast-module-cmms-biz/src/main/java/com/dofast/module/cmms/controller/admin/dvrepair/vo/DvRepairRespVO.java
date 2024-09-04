package com.dofast.module.cmms.controller.admin.dvrepair.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 设备维修单 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class DvRepairRespVO extends DvRepairBaseVO {

    @Schema(description = "维修单ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "13153")
    private Long id;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

}
