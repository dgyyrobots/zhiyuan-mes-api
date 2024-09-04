package com.dofast.module.mes.controller.admin.mdvendor.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 供应商 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class MdVendorRespVO extends MdVendorBaseVO {

    @Schema(description = "供应商ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "26219")
    private Long id;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

}
