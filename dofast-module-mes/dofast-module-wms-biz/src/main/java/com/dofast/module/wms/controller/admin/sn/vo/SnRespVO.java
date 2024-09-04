package com.dofast.module.wms.controller.admin.sn.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - SN码 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class SnRespVO extends SnBaseVO {

    @Schema(description = "SN码ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "23765")
    private Long id;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

}
