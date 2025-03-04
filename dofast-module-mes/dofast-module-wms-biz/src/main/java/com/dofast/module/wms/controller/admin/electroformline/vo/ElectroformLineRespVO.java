package com.dofast.module.wms.controller.admin.electroformline.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 制版房领料单身体 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ElectroformLineRespVO extends ElectroformLineBaseVO {

    @Schema(description = "行ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "24532")
    private Long id;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

}
