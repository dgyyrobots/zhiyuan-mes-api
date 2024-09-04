package com.dofast.module.hr.controller.admin.employeefamiles.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 员工家庭成员更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class EmployeeFamilesUpdateReqVO extends EmployeeFamilesBaseVO {

    @Schema(description = "家庭成员id", requiredMode = Schema.RequiredMode.REQUIRED, example = "14114")
    @NotNull(message = "家庭成员id不能为空")
    private Long id;

    @Schema(description = "用户id", requiredMode = Schema.RequiredMode.REQUIRED, example = "10976")
    @NotNull(message = "用户id不能为空")
    private Long userId;
}
