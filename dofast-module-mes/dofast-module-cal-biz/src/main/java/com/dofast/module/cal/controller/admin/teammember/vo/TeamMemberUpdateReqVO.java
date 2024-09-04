package com.dofast.module.cal.controller.admin.teammember.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 班组成员更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class TeamMemberUpdateReqVO extends TeamMemberBaseVO {

    @Schema(description = "班组成员ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "3936")
    @NotNull(message = "班组成员ID不能为空")
    private Long id;

}
