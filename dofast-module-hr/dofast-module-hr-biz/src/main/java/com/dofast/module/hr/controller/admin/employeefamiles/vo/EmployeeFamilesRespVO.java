package com.dofast.module.hr.controller.admin.employeefamiles.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 员工家庭成员 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class EmployeeFamilesRespVO extends EmployeeFamilesBaseVO {

    @Schema(description = "家庭成员id", requiredMode = Schema.RequiredMode.REQUIRED, example = "14114")
    private Long id;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

}
