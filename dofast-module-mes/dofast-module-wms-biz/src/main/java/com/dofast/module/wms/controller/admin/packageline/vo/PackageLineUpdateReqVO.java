package com.dofast.module.wms.controller.admin.packageline.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 装箱明细更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class PackageLineUpdateReqVO extends PackageLineBaseVO {

    @Schema(description = "明细行ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "21355")
    @NotNull(message = "明细行ID不能为空")
    private Long id;

}
