package com.dofast.module.finance.controller.admin.cash.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 发票信息更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CashInvoiceUpdateReqVO extends CashInvoiceBaseVO {

    @Schema(description = "发票信息id", requiredMode = Schema.RequiredMode.REQUIRED, example = "28544")
    @NotNull(message = "发票信息id不能为空")
    private Long id;

}
