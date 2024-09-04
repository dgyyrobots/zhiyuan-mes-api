package com.dofast.module.hr.controller.admin.salarycommission.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 绩效工资更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class SalarycommissionUpdateReqVO extends SalarycommissionBaseVO {

    @Schema(description = "绩效工资id", requiredMode = Schema.RequiredMode.REQUIRED, example = "13283")
    @NotNull(message = "绩效工资id不能为空")
    private Integer id;

}
