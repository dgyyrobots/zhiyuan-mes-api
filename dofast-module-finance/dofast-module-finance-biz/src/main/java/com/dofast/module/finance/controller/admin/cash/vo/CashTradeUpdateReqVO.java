package com.dofast.module.finance.controller.admin.cash.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 财务流水更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CashTradeUpdateReqVO extends CashTradeBaseVO {

    @Schema(description = "资金明细id", requiredMode = Schema.RequiredMode.REQUIRED, example = "20574")
    @NotNull(message = "资金明细id不能为空")
    private Long id;

}
