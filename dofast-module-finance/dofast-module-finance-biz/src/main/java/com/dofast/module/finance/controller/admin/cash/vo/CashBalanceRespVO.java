package com.dofast.module.finance.controller.admin.cash.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 现金余额 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CashBalanceRespVO extends CashBalanceBaseVO {

    @Schema(description = "余额明细id", requiredMode = Schema.RequiredMode.REQUIRED, example = "13643")
    private Long id;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDateTime createTime;

}
