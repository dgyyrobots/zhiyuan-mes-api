package com.dofast.module.hr.controller.admin.commissionrule.vo;

import java.time.LocalDate;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 提成规则 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CommissionruleRespVO extends CommissionruleBaseVO {

    @Schema(description = "提成规则id", requiredMode = Schema.RequiredMode.REQUIRED, example = "21469")
    private Integer id;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDateTime createTime;

}
