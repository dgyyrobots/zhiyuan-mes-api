package com.dofast.module.cmms.controller.admin.dvmachinerytype.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 设备类型 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class DvMachineryTypeRespVO extends DvMachineryTypeBaseVO {

    @Schema(description = "设备类型ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "22250")
    private Long id;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

}
