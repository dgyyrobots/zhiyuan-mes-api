package com.dofast.module.pro.controller.admin.task.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 生产任务创建 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class TaskCreateReqVO extends TaskBaseVO {
    @Schema(description = "任务ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "20491")
    private Long id;
}
