package com.dofast.module.sales.controller.admin.bpm.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 售后工作流流程表单 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class OrderAfterBpmRespVO extends OrderAfterBpmBaseVO {

    @Schema(description = "售后表单主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "11666")
    private Long id;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDateTime createTime;

}
