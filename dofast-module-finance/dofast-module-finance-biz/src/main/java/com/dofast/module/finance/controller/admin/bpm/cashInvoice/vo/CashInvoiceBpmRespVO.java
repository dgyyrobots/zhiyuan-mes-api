package com.dofast.module.finance.controller.admin.bpm.cashInvoice.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 发票信息 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CashInvoiceBpmRespVO extends CashInvoiceBpmBaseVO {

    @Schema(description = "发票信息id", requiredMode = Schema.RequiredMode.REQUIRED, example = "28544")
    private Long id;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDateTime createTime;

}
