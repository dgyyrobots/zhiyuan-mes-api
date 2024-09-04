package com.dofast.module.wms.controller.admin.barcode.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 条码清单 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class BarcodeRespVO extends BarcodeBaseVO {

    @Schema(description = "条码ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "14867")
    private Long id;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

}
