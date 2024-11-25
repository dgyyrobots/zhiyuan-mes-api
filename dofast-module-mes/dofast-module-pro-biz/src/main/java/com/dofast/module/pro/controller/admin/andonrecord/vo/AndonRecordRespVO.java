package com.dofast.module.pro.controller.admin.andonrecord.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 安灯呼叫记录 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class AndonRecordRespVO extends AndonRecordBaseVO {

    @Schema(description = "记录ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "8063")
    private Long recordId;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

}
