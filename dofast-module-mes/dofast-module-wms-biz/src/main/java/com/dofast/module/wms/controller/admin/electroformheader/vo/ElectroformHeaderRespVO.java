package com.dofast.module.wms.controller.admin.electroformheader.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 制版房领料单头 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ElectroformHeaderRespVO extends ElectroformHeaderBaseVO {

    @Schema(description = "制版领料单ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "23303")
    private Long id;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

}
