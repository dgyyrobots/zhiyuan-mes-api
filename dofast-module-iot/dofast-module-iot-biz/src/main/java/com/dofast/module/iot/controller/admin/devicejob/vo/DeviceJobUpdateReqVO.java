package com.dofast.module.iot.controller.admin.devicejob.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 设备定时更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class DeviceJobUpdateReqVO extends DeviceJobBaseVO {

    @Schema(description = "任务ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "13032")
    @NotNull(message = "任务ID不能为空")
    private Long id;

    @Schema(description = "任务名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "张三")
    @NotNull(message = "任务名称不能为空")
    private String jobName;

    @Schema(description = "任务组名", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "任务组名不能为空")
    private String jobGroup;

}
