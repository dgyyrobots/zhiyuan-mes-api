package com.dofast.module.wms.controller.admin.rtsalse.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 产品销售退货单 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class RtSalseRespVO extends RtSalseBaseVO {

    @Schema(description = "退货单ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "31414")
    private Long id;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

}
