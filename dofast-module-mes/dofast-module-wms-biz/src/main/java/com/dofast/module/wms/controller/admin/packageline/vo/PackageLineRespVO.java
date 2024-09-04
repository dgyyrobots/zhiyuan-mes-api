package com.dofast.module.wms.controller.admin.packageline.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 装箱明细 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class PackageLineRespVO extends PackageLineBaseVO {

    @Schema(description = "明细行ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "21355")
    private Long id;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

}
