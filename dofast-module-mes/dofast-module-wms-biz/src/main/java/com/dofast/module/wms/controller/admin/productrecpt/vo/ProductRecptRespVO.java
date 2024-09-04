package com.dofast.module.wms.controller.admin.productrecpt.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 产品入库录 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ProductRecptRespVO extends ProductRecptBaseVO {

    @Schema(description = "入库单ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "27945")
    private Long id;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

}
