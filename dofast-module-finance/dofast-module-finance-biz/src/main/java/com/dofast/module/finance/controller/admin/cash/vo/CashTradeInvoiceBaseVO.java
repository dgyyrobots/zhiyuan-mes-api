package com.dofast.module.finance.controller.admin.cash.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import javax.validation.constraints.*;

/**
 * 业务发票关联 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class CashTradeInvoiceBaseVO {

    @Schema(description = "渠道", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "渠道不能为空")
    private Long trade;

    @Schema(description = "发票", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "发票不能为空")
    private Long invoice;

}
