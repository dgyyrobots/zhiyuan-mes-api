package com.dofast.module.wms.controller.admin.package1.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 装箱单更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class PackageUpdateReqVO extends PackageBaseVO {

    @Schema(description = "装箱单ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "6063")
    @NotNull(message = "装箱单ID不能为空")
    private Long id;

}
