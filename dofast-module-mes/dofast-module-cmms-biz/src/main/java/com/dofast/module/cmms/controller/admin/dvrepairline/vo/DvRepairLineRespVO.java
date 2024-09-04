package com.dofast.module.cmms.controller.admin.dvrepairline.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 设备维修单行 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class DvRepairLineRespVO extends DvRepairLineBaseVO {

    @Schema(description = "行ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "24340")
    private Long id;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

}
