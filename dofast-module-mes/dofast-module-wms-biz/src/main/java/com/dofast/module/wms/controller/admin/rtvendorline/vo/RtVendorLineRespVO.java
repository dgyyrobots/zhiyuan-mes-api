package com.dofast.module.wms.controller.admin.rtvendorline.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 供应商退货行 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class RtVendorLineRespVO extends RtVendorLineBaseVO {

    @Schema(description = "行ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "21759")
    private Long id;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

}
