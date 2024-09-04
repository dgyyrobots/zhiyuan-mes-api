package com.dofast.module.iot.controller.admin.alertlog.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 设备告警日志更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class AlertLogUpdateReqVO extends AlertLogBaseVO {

    @Schema(description = "告警ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "16118")
    @NotNull(message = "告警ID不能为空")
    private Long id;

}
