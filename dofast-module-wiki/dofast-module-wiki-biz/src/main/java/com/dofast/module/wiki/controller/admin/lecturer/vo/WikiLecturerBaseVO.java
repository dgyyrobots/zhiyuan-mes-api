package com.dofast.module.wiki.controller.admin.lecturer.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import javax.validation.constraints.*;

/**
 * 讲师的信息	 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class WikiLecturerBaseVO {

    @Schema(description = "讲师名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "张三")
    @NotNull(message = "讲师名称不能为空")
    private String name;

    @Schema(description = "讲师授课方向", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "讲师授课方向不能为空")
    private String direction;

    @Schema(description = "讲师照片", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "讲师照片不能为空")
    private String picture;

}
