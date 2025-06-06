package com.dofast.module.wms.controller.admin.itemconsumeline.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 物料消耗记录行 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ItemConsumeLineRespVO extends ItemConsumeLineBaseVO {

    @Schema(description = "行ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "15975")
    private Long id;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

}
