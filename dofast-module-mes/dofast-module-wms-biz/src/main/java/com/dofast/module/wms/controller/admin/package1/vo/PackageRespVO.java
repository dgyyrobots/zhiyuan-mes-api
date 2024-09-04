package com.dofast.module.wms.controller.admin.package1.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 装箱单 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class PackageRespVO extends PackageBaseVO {

    @Schema(description = "装箱单ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "6063")
    private Long id;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

}
