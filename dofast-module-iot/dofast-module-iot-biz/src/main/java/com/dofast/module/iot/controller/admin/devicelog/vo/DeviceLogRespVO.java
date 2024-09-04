package com.dofast.module.iot.controller.admin.devicelog.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 设备日志 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class DeviceLogRespVO extends DeviceLogBaseVO {

    @Schema(description = "设备日志ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "20565")
    private Long id;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDateTime createTime;

}
