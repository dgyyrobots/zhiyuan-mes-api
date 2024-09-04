package com.dofast.module.iot.controller.admin.devicelog.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 设备日志更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class DeviceLogUpdateReqVO extends DeviceLogBaseVO {

    @Schema(description = "设备日志ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "20565")
    @NotNull(message = "设备日志ID不能为空")
    private Long id;

}
