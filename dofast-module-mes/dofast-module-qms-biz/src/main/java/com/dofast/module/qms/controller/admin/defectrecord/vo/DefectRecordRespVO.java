package com.dofast.module.qms.controller.admin.defectrecord.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 检验单缺陷记录 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class DefectRecordRespVO extends DefectRecordBaseVO {

    @Schema(description = "缺陷ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "7251")
    private Long id;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

}
