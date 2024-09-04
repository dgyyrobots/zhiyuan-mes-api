package com.dofast.module.wiki.controller.admin.coursewarefile.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import javax.validation.constraints.*;

/**
 * 课件文件的保存地址 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class CoursewareFileBaseVO {

    @Schema(description = "课件id", requiredMode = Schema.RequiredMode.REQUIRED, example = "13310")
    @NotNull(message = "课件id不能为空")
    private Long coursewareId;

    @Schema(description = "文件名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "李四")
    @NotNull(message = "文件名称不能为空")
    private String filename;

    @Schema(description = "文件路径", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "文件路径不能为空")
    private String filepath;

    @Schema(description = "文件大小")
    private String fileSize;

}
