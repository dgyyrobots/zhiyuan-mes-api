package com.dofast.module.cal.controller.admin.shift.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 计划班次更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ShiftUpdateReqVO extends ShiftBaseVO {

    @Schema(description = "班次ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "4669")
    @NotNull(message = "班次ID不能为空")
    private Long id;

}
