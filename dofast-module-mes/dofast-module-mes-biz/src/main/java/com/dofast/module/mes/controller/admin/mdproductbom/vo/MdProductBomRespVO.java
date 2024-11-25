package com.dofast.module.mes.controller.admin.mdproductbom.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 产品BOM关系 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class MdProductBomRespVO extends MdProductBomBaseVO {

    @Schema(description = "流水号", requiredMode = Schema.RequiredMode.REQUIRED, example = "29402")
    private Long id;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

}
