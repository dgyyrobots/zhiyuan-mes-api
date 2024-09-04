package com.dofast.module.trade.controller.admin.electronicsheetpackage.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Schema(description = "管理后台 - 电子面单更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ElectronicsheetPackageUpdateReqVO extends ElectronicsheetPackageBaseVO {

    @Schema(description = "自增编码id", requiredMode = Schema.RequiredMode.REQUIRED, example = "4305")
    @NotNull(message = "自增编码id不能为空")
    private Integer id;

}
