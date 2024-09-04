package com.dofast.module.hr.controller.admin.employeebasic.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 员工基本信息更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class EmployeeBasicUpdateReqVO extends EmployeeBasicBaseVO {

    @Schema(description = "头像", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "头像不能为空")
    private String nickImg;

    @Schema(description = "员工id", requiredMode = Schema.RequiredMode.REQUIRED, example = "15467")
    @NotNull(message = "员工id不能为空")
    private Long id;

}
