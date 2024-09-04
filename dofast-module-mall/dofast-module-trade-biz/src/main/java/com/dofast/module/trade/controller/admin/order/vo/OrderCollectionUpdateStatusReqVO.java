package com.dofast.module.trade.controller.admin.order.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Schema(description = "管理后台 - 采集任务更新 Request VO")
@Data
@ToString(callSuper = true)
public class OrderCollectionUpdateStatusReqVO {

    @Schema(description = "ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "26347")
    @NotNull(message = "ID不能为空")
    private Integer id;

    @Schema(description = "任务状态", requiredMode = Schema.RequiredMode.REQUIRED, example = "created")
    @NotNull(message = "任务状态不能为空")
    private String status;
}
