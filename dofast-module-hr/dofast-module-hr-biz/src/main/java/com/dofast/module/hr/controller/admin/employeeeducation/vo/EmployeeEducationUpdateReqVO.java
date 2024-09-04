package com.dofast.module.hr.controller.admin.employeeeducation.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 员工教育培训经历更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class EmployeeEducationUpdateReqVO extends EmployeeEducationBaseVO {

    @Schema(description = "培训教育经历id", requiredMode = Schema.RequiredMode.REQUIRED, example = "6140")
    @NotNull(message = "培训教育经历id不能为空")
    private Long id;

    @Schema(description = "用户id", requiredMode = Schema.RequiredMode.REQUIRED, example = "13788")
    @NotNull(message = "用户id不能为空")
    private Long userId;
}
