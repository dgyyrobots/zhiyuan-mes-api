package com.dofast.module.channel.controller.admin.ordergoods.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Schema(description = "管理后台 - 子订单 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class OrderGoodsRespVO extends OrderGoodsBaseVO {

    @Schema(description = "订单商品", requiredMode = Schema.RequiredMode.REQUIRED, example = "5094")
    private Integer id;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDateTime createTime;

}
