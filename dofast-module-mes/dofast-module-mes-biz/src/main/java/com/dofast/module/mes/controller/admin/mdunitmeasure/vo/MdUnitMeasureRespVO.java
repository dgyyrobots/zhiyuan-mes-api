package com.dofast.module.mes.controller.admin.mdunitmeasure.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 单位 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class MdUnitMeasureRespVO extends MdUnitMeasureBaseVO {

    @Schema(description = "单位ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "12928")
    private Long id;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

}
