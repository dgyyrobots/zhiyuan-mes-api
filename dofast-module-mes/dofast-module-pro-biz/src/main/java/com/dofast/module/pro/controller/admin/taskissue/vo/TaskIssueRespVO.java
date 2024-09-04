package com.dofast.module.pro.controller.admin.taskissue.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 生产任务投料 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class TaskIssueRespVO extends TaskIssueBaseVO {

    @Schema(description = "记录ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "20270")
    private Long id;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

}
