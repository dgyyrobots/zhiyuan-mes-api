package com.dofast.module.mes.controller.admin.mdworkstationworker.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 人力资源 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class MdWorkstationWorkerRespVO extends MdWorkstationWorkerBaseVO {

    @Schema(description = "记录ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "15050")
    private Long id;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

}
