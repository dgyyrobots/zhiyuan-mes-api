package com.dofast.module.finance.controller.admin.bpm.cashTrade.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 财务流水 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CashTradeBpmRespVO extends CashTradeBpmBaseVO {

    @Schema(description = "资金明细id", requiredMode = Schema.RequiredMode.REQUIRED, example = "20574")
    private Long id;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDateTime createTime;

}
