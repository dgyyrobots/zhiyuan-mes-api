package com.dofast.module.cal.controller.admin.team.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 班组更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class TeamUpdateReqVO extends TeamBaseVO {

    @Schema(description = "班组ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "15224")
    @NotNull(message = "班组ID不能为空")
    private Long id;

}
