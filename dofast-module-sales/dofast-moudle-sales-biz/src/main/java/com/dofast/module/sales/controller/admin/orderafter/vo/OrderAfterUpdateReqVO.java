package com.dofast.module.sales.controller.admin.orderafter.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 售后流程表单更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class OrderAfterUpdateReqVO extends OrderAfterBaseVO {

    @Schema(description = "售后表单主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "11666")
    @NotNull(message = "售后表单主键不能为空")
    private Long id;

}
