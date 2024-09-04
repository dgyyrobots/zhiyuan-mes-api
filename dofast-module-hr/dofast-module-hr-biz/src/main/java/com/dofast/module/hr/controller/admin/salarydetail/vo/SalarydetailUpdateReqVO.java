package com.dofast.module.hr.controller.admin.salarydetail.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 工资明细更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class SalarydetailUpdateReqVO extends SalarydetailBaseVO {

    @Schema(description = "工资明细id", requiredMode = Schema.RequiredMode.REQUIRED, example = "6464")
    @NotNull(message = "工资明细id不能为空")
    private Integer id;

}
