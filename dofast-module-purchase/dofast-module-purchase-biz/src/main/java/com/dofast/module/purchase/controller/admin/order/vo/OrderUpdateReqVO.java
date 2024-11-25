package com.dofast.module.purchase.controller.admin.order.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 采购订单更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class OrderUpdateReqVO extends OrderBaseVO {

    @Schema(description = "资金账户id", requiredMode = Schema.RequiredMode.REQUIRED, example = "8225")
    @NotNull(message = "资金账户id不能为空")
    private Integer id;

}
