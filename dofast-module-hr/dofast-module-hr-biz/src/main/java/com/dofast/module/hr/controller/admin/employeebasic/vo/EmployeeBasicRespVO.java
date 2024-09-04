package com.dofast.module.hr.controller.admin.employeebasic.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 员工基本信息 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class EmployeeBasicRespVO extends EmployeeBasicBaseVO {

    @Schema(description = "员工id", requiredMode = Schema.RequiredMode.REQUIRED, example = "15467")
    private Long id;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

}
