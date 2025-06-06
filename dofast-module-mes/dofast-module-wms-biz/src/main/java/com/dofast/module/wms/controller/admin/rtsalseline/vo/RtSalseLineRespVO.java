package com.dofast.module.wms.controller.admin.rtsalseline.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 产品销售退货行 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class RtSalseLineRespVO extends RtSalseLineBaseVO {

    @Schema(description = "行ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "15022")
    private Long id;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

}
