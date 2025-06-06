package com.dofast.module.pro.controller.admin.workorderbom.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 生产工单BOM组成 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class WorkorderBomRespVO extends WorkorderBomBaseVO {

    @Schema(description = "行ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "29705")
    private Long id;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

}
