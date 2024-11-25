package com.dofast.module.wms.controller.admin.feedline.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 上料详情 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class FeedLineRespVO extends FeedLineBaseVO {

    @Schema(description = "行ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "14669")
    private Long id;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

}
