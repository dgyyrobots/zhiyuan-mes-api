package com.dofast.module.wms.controller.admin.transferline.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 转移单行 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class TransferLineRespVO extends TransferLineBaseVO {

    @Schema(description = "明细行ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "12002")
    private Long id;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

}
