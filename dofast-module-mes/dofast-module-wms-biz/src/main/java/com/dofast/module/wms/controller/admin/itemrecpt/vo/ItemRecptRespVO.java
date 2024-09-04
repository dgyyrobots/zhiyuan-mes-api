package com.dofast.module.wms.controller.admin.itemrecpt.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 物料入库单 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ItemRecptRespVO extends ItemRecptBaseVO {

    @Schema(description = "入库单ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "12747")
    private Long id;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

}
