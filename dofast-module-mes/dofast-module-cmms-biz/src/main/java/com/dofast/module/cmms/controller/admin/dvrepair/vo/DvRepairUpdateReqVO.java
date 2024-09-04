package com.dofast.module.cmms.controller.admin.dvrepair.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 设备维修单更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class DvRepairUpdateReqVO extends DvRepairBaseVO {

    @Schema(description = "维修单ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "13153")
    @NotNull(message = "维修单ID不能为空")
    private Long id;

}
