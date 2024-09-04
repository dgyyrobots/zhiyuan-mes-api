package com.dofast.module.finance.controller.admin.cash.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 现金余额更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CashBalanceUpdateReqVO extends CashBalanceBaseVO {

    @Schema(description = "余额明细id", requiredMode = Schema.RequiredMode.REQUIRED, example = "13643")
    @NotNull(message = "余额明细id不能为空")
    private Long id;

    @Schema(description = "当前余额", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "当前余额不能为空")
    private String currency;

}
