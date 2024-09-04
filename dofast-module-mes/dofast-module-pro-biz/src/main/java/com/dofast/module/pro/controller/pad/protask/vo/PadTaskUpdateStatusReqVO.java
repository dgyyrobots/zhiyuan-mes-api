package com.dofast.module.pro.controller.pad.protask.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class PadTaskUpdateStatusReqVO {
    @Schema(description = "任务ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "任务ID不能为空")
    private Long taskId;
    @Schema(description = "任务状态", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "任务状态不能为空")
    private String status;
}
