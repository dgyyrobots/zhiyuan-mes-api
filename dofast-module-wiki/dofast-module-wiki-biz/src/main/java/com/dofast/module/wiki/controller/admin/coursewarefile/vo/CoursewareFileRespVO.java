package com.dofast.module.wiki.controller.admin.coursewarefile.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 课件文件的保存地址 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CoursewareFileRespVO extends CoursewareFileBaseVO {

    @Schema(description = "文件id", requiredMode = Schema.RequiredMode.REQUIRED, example = "19134")
    private Long id;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDateTime createTime;

}
