package com.dofast.module.finance.controller.admin.cash.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 财务发票明细更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CashInvoiceDetailUpdateReqVO extends CashInvoiceDetailBaseVO {

    @Schema(description = "发票明细id", requiredMode = Schema.RequiredMode.REQUIRED, example = "7885")
    @NotNull(message = "发票明细id不能为空")
    private Long id;

}
