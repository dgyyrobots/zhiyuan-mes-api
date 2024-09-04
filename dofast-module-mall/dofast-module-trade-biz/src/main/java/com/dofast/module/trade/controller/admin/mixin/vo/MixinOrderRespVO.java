package com.dofast.module.trade.controller.admin.mixin.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@Schema(description = "管理后台 - 销售订单 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class MixinOrderRespVO extends MixinOrderBaseVO {

    @Schema(description = "销售订单ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "25322")
    private Long id;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDateTime createTime;

    private List<String> tradeOrderNos;

    @Schema(description = "销售出库单是否打印")
    private Integer productSalseCount;

    @Schema(description = "出货标签是否打印")
    private Integer outboundLabelCount;

}
