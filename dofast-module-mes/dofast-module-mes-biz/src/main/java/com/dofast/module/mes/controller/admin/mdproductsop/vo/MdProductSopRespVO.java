package com.dofast.module.mes.controller.admin.mdproductsop.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 产品SOP Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class MdProductSopRespVO extends MdProductSopBaseVO {

    @Schema(description = "记录ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "9444")
    private Long id;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

}
