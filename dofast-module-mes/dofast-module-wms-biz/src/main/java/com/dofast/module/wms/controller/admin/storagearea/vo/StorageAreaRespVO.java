package com.dofast.module.wms.controller.admin.storagearea.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 库位 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class StorageAreaRespVO extends StorageAreaBaseVO {

    @Schema(description = "库位ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "6408")
    private Long id;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

}
