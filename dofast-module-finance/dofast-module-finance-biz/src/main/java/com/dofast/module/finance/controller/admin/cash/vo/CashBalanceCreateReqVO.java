package com.dofast.module.finance.controller.admin.cash.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 现金余额创建 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CashBalanceCreateReqVO extends CashBalanceBaseVO {

    @Schema(description = "当前余额", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "当前余额不能为空")
    private String currency;

}
