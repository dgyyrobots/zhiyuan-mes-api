package com.dofast.module.iot.controller.admin.firmware.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 产品固件 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class FirmwareRespVO extends FirmwareBaseVO {

    @Schema(description = "固件ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "21698")
    private Long id;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDateTime createTime;

}
