package com.dofast.module.purchase.controller.admin.order.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 采购订单 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class OrderRespVO extends OrderBaseVO {

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDateTime createTime;

    @Schema(description = "资金账户id", requiredMode = Schema.RequiredMode.REQUIRED, example = "8225")
    private Integer id;

}
