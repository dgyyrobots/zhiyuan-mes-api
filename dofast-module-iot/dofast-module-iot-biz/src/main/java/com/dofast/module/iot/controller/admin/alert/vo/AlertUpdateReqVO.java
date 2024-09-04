package com.dofast.module.iot.controller.admin.alert.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 设备告警更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class AlertUpdateReqVO extends AlertBaseVO {

    @Schema(description = "告警ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "25086")
    @NotNull(message = "告警ID不能为空")
    private Long id;

}
