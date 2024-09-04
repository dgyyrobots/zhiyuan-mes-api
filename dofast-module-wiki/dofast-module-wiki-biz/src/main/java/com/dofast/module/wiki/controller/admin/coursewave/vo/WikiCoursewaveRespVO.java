package com.dofast.module.wiki.controller.admin.coursewave.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 首页知识列表的信息	 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class WikiCoursewaveRespVO extends WikiCoursewaveBaseVO {

    @Schema(description = "课件id", requiredMode = Schema.RequiredMode.REQUIRED, example = "10293")
    private Long id;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDateTime createTime;

}
