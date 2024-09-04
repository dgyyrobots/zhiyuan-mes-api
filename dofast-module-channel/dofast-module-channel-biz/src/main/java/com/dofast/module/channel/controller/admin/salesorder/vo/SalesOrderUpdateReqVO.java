package com.dofast.module.channel.controller.admin.salesorder.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 销售订单更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class SalesOrderUpdateReqVO extends SalesOrderBaseVO {

    @Schema(description = "销售订单ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "14738")
    @NotNull(message = "销售订单ID不能为空")
    private Long id;

}
