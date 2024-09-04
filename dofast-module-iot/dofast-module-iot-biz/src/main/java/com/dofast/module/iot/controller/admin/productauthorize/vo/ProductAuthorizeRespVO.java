package com.dofast.module.iot.controller.admin.productauthorize.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 产品授权码 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ProductAuthorizeRespVO extends ProductAuthorizeBaseVO {

    @Schema(description = "授权码ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "29927")
    private Long id;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDateTime createTime;

}
