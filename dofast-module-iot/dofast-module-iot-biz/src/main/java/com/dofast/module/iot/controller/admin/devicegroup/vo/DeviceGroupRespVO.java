package com.dofast.module.iot.controller.admin.devicegroup.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 设备分组 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class DeviceGroupRespVO extends DeviceGroupBaseVO {

    @Schema(description = "设备ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "27619")
    private Long id;

    @Schema(description = "分组ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "13835")
    private Long groupId;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDateTime createTime;

}
