package com.dofast.module.hr.controller.admin.salarycommission.vo;

import java.time.LocalDate;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 绩效工资 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class SalarycommissionRespVO extends SalarycommissionBaseVO {

    @Schema(description = "绩效工资id", requiredMode = Schema.RequiredMode.REQUIRED, example = "13283")
    private Integer id;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDateTime createTime;

}
