package com.dofast.module.wms.controller.admin.itemconsume.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 物料消耗记录 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ItemConsumeRespVO extends ItemConsumeBaseVO {

    @Schema(description = "记录ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "18780")
    private Long id;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

}
