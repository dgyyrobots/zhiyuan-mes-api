package com.dofast.module.wms.controller.admin.rtissueline.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 生产退料单行 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class RtIssueLineRespVO extends RtIssueLineBaseVO {

    @Schema(description = "行ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "22834")
    private Long id;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

}
