package com.dofast.module.finance.controller.admin.cash.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 资金账号更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CashDepositorUpdateReqVO extends CashDepositorBaseVO {

    @Schema(description = "资金账户id", requiredMode = Schema.RequiredMode.REQUIRED, example = "30574")
    @NotNull(message = "资金账户id不能为空")
    private Long id;

}
