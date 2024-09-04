package com.dofast.module.pro.controller.admin.transorder.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 流转单 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class TransOrderRespVO extends TransOrderBaseVO {

    @Schema(description = "流转单ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "17276")
    private Long id;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

}
