package com.dofast.module.sales.controller.admin.bpm.vo;

import com.dofast.module.sales.controller.admin.orderafter.vo.OrderAfterBaseVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Schema(description = "管理后台 - 售后流程表单更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class OrderAfterBpmUpdateReqVO extends OrderAfterBpmBaseVO {

    @Schema(description = "售后表单主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "11666")
    @NotNull(message = "售后表单主键不能为空")
    private Long id;

}
