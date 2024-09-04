package com.dofast.module.qms.controller.admin.defect.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 常见缺陷 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class DefectRespVO extends DefectBaseVO {

    @Schema(description = "缺陷ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "16830")
    private Long id;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

}
