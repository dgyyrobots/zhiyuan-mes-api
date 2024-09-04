package com.dofast.module.iot.controller.admin.devicegroup.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 设备分组更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class DeviceGroupUpdateReqVO extends DeviceGroupBaseVO {

    @Schema(description = "设备ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "27619")
    @NotNull(message = "设备ID不能为空")
    private Long id;

    @Schema(description = "分组ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "13835")
    @NotNull(message = "分组ID不能为空")
    private Long groupId;

}
