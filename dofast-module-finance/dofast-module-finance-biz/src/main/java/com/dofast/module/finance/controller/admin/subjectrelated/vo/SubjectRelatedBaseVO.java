package com.dofast.module.finance.controller.admin.subjectrelated.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import javax.validation.constraints.*;

/**
 * 收支科目 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class SubjectRelatedBaseVO {

    @Schema(description = "科目名称", example = "张三")
    private String subjectName;

    @Schema(description = "科目类型(in收 out支)（0/1）", example = "1")
    private Integer subjectType;

}
