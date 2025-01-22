package com.dofast.module.pro.controller.admin.task.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 生产任务 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class TaskRespVO extends TaskBaseVO {

    @Schema(description = "任务ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "20491")
    private Long id;

    @Schema(description = "是否已报工",requiredMode = Schema.RequiredMode.REQUIRED,example = "true")
    private Integer isReport;

    @Schema(description = "订单编码",requiredMode = Schema.RequiredMode.REQUIRED,example = "true")
    private String sourceCode;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;


}
