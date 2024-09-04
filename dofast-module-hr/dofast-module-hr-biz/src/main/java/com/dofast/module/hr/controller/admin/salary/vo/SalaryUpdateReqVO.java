package com.dofast.module.hr.controller.admin.salary.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 工资总更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class SalaryUpdateReqVO extends SalaryBaseVO {

    @Schema(description = "工资明细id", requiredMode = Schema.RequiredMode.REQUIRED, example = "30350")
    @NotNull(message = "工资明细id不能为空")
    private Integer id;

}
