package com.dofast.module.system.controller.admin.form.vo;

import java.time.LocalDate;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import javax.validation.constraints.*;

/**
 * 表单历史 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class FormRecordBaseVO {

    @Schema(description = "计价规则", requiredMode = Schema.RequiredMode.REQUIRED, example = "2290")
    @NotNull(message = "计价规则不能为空")
    private Long formId;

    @Schema(description = "表单内容", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "表单内容不能为空")
    private String value;

}
