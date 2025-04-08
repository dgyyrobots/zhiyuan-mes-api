package com.dofast.module.purchase.controller.admin.retreatOrder.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - ERP仓退单更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class RetreatOrderUpdateReqVO extends RetreatOrderBaseVO {

    @Schema(description = "ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "31678")
    @NotNull(message = "ID不能为空")
    private Long id;

}
