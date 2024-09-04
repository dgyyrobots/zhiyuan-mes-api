package com.dofast.module.cal.controller.admin.teamshift.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 班组排班更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class TeamshiftUpdateReqVO extends TeamshiftBaseVO {

    @Schema(description = "流水号id", requiredMode = Schema.RequiredMode.REQUIRED, example = "24076")
    @NotNull(message = "流水号id不能为空")
    private Long id;

}
