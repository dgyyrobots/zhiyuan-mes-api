package com.dofast.module.wms.controller.admin.packageline.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 装箱明细创建 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class PackageLineCreateReqVO extends PackageLineBaseVO {
    @Schema(description = "明细行ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "21355")
    private Long id;
}
