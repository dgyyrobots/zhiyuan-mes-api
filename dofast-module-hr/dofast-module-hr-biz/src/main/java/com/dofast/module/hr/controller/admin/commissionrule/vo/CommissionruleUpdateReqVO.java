package com.dofast.module.hr.controller.admin.commissionrule.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 提成规则更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CommissionruleUpdateReqVO extends CommissionruleBaseVO {

    @Schema(description = "提成规则id", requiredMode = Schema.RequiredMode.REQUIRED, example = "21469")
    @NotNull(message = "提成规则id不能为空")
    private Integer id;

}
