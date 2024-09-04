package com.dofast.module.hr.controller.admin.employeebasic.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 员工基本信息创建 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class EmployeeBasicCreateReqVO extends EmployeeBasicBaseVO {

    @Schema(description = "头像", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "头像不能为空")
    private String nickImg;

}
