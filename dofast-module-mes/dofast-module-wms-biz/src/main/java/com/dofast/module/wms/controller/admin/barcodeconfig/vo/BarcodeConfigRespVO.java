package com.dofast.module.wms.controller.admin.barcodeconfig.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 条码配置 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class BarcodeConfigRespVO extends BarcodeConfigBaseVO {

    @Schema(description = "配置ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "24520")
    private Long id;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

}
