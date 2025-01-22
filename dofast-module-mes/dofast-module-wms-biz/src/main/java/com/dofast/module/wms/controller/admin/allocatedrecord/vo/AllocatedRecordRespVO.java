package com.dofast.module.wms.controller.admin.allocatedrecord.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 调拨单身记录 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class AllocatedRecordRespVO extends AllocatedRecordBaseVO {

    @Schema(description = "行ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "7532")
    private Long id;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

}
