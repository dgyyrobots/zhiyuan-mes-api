package com.dofast.module.purchase.controller.admin.invoice.vo;

import java.time.LocalDate;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 采购入库单 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class InvoiceRespVO extends InvoiceBaseVO {

    @Schema(description = "id", requiredMode = Schema.RequiredMode.REQUIRED, example = "29548")
    private Integer id;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDateTime createTime;

}
