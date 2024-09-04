package com.dofast.module.iot.controller.admin.alert.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 设备告警 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class AlertRespVO extends AlertBaseVO {

    @Schema(description = "告警ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "25086")
    private Long id;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDateTime createTime;

}
