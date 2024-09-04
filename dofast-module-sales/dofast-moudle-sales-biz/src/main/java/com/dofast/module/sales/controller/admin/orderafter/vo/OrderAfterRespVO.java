package com.dofast.module.sales.controller.admin.orderafter.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 售后流程表单 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class OrderAfterRespVO extends OrderAfterBaseVO {

    @Schema(description = "售后表单主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "11666")
    private Long id;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDateTime createTime;

}
