package com.dofast.module.wms.controller.admin.storagelocation.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 库区 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class StorageLocationRespVO extends StorageLocationBaseVO {

    @Schema(description = "库区ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "18526")
    private Long id;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

}
