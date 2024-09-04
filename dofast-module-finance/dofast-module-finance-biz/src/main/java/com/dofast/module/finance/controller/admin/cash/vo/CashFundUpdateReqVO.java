package com.dofast.module.finance.controller.admin.cash.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 财务退款更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CashFundUpdateReqVO extends CashFundBaseVO {

    @Schema(description = "退款明细id", requiredMode = Schema.RequiredMode.REQUIRED, example = "19826")
    @NotNull(message = "退款明细id不能为空")
    private Long id;

}
