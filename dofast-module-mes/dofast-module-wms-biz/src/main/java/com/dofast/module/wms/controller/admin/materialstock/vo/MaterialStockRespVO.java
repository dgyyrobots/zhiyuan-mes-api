package com.dofast.module.wms.controller.admin.materialstock.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 库存记录 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class MaterialStockRespVO extends MaterialStockBaseVO {

    @Schema(description = "事务ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "31507")
    private Long id;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

}
