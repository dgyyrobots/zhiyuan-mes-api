package com.dofast.module.purchase.controller.admin.retreatOrder.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - ERP仓退单 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class RetreatOrderRespVO extends RetreatOrderBaseVO {

    @Schema(description = "ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "31678")
    private Long id;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

}
