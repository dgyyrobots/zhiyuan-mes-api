package com.dofast.module.pro.controller.admin.routeproduct.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 产品制程 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class RouteProductRespVO extends RouteProductBaseVO {

    @Schema(description = "记录ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "28991")
    private Long id;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

}
