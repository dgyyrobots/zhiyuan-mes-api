package com.dofast.module.pro.controller.admin.routeproductbom.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 产品制程物料BOM Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class RouteProductBomRespVO extends RouteProductBomBaseVO {

    @Schema(description = "记录ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "7136")
    private Long id;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

}
