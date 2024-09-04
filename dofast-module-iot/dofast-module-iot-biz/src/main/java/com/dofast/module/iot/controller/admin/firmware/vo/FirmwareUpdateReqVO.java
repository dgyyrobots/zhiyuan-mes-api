package com.dofast.module.iot.controller.admin.firmware.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 产品固件更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class FirmwareUpdateReqVO extends FirmwareBaseVO {

    @Schema(description = "固件ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "21698")
    @NotNull(message = "固件ID不能为空")
    private Long id;

}
