package com.dofast.module.pro.controller.admin.task.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class TaskUpdateStatusReqVO {
    @Schema(description = "任务ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "任务ID不能为空")
    private Long id;
    @Schema(description = "任务状态", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "任务状态不能为空")
    private String status;
}
