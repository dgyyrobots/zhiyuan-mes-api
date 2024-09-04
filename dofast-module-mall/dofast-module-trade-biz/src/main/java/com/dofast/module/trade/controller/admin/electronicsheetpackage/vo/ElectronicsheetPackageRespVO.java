package com.dofast.module.trade.controller.admin.electronicsheetpackage.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 电子面单 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ElectronicsheetPackageRespVO extends ElectronicsheetPackageBaseVO {

    @Schema(description = "自增编码id", requiredMode = Schema.RequiredMode.REQUIRED, example = "4305")
    private Integer id;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDateTime createTime;

}
