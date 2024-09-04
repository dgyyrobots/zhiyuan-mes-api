package com.dofast.module.wms.controller.admin.package1.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 装箱单创建 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class PackageCreateReqVO extends PackageBaseVO {
    @Schema(description = "装箱单ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "6063")
    private Long id;
}
