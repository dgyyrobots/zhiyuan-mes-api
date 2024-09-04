package com.dofast.module.hr.controller.admin.employeeeducation.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 员工教育培训经历 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class EmployeeEducationRespVO extends EmployeeEducationBaseVO {

    @Schema(description = "培训教育经历id", requiredMode = Schema.RequiredMode.REQUIRED, example = "6140")
    private Long id;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

}
