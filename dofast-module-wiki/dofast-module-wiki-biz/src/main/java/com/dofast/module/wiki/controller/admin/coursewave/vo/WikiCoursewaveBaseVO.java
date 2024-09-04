package com.dofast.module.wiki.controller.admin.coursewave.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import javax.validation.constraints.*;

/**
 * 首页知识列表的信息	 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class WikiCoursewaveBaseVO {

    @Schema(description = "种类id", example = "31158")
    private Long categoryId;

    @Schema(description = "讲师ID",example = "1")
    @NotNull(message = "讲师ID不能为空")
    private Long lecturerId;


    @Schema(description = "课件名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "王五")
    @NotNull(message = "课件名称不能为空")
    private String coursewareName;




}
