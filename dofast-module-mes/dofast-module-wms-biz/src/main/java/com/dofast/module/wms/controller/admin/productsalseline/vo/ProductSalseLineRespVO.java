package com.dofast.module.wms.controller.admin.productsalseline.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 产品销售出库行 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ProductSalseLineRespVO extends ProductSalseLineBaseVO {

    @Schema(description = "行ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "25111")
    private Long id;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

}
