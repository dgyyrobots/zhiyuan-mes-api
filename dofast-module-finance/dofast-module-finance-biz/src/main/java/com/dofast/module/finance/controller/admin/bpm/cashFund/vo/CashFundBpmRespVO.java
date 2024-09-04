package com.dofast.module.finance.controller.admin.bpm.cashFund.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 财务退款 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CashFundBpmRespVO extends CashFundBpmBaseVO {

    @Schema(description = "退款明细id", requiredMode = Schema.RequiredMode.REQUIRED, example = "19826")
    private Long id;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDateTime createTime;

}
