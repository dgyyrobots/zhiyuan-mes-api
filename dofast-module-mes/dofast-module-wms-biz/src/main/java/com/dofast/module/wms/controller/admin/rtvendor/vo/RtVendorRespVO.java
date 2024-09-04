package com.dofast.module.wms.controller.admin.rtvendor.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 供应商退货 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class RtVendorRespVO extends RtVendorBaseVO {

    @Schema(description = "退货单ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "23729")
    private Long id;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

}
