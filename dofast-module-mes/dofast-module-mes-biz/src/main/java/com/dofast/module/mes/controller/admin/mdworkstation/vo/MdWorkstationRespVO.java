package com.dofast.module.mes.controller.admin.mdworkstation.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 工作站 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class MdWorkstationRespVO extends MdWorkstationBaseVO {

    @Schema(description = "工作站ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "43")
    private Long id;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

}
