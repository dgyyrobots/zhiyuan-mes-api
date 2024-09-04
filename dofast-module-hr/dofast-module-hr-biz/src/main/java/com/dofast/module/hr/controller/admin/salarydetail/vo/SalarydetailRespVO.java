package com.dofast.module.hr.controller.admin.salarydetail.vo;

import java.time.LocalDate;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 工资明细 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class SalarydetailRespVO extends SalarydetailBaseVO {

    @Schema(description = "工资明细id", requiredMode = Schema.RequiredMode.REQUIRED, example = "6464")
    private Integer id;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDateTime createTime;

}
