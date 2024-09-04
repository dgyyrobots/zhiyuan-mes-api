package com.dofast.module.wiki.controller.admin.category.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 首页的分类 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class WikiCategoryRespVO extends WikiCategoryBaseVO {

    @Schema(description = "种类id", requiredMode = Schema.RequiredMode.REQUIRED, example = "18920")
    private Long id;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDateTime createTime;

}
