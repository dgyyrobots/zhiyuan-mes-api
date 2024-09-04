package com.dofast.module.wms.controller.admin.productsalse.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 销售出库单 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ProductSalseRespVO extends ProductSalseBaseVO {

    @Schema(description = "出库单ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "18915")
    private Long id;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

}
