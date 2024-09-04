package com.dofast.module.system.controller.admin.form.vo;

import java.time.LocalDate;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import javax.validation.constraints.*;

/**
 * 字段 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class FormItemBaseVO {

    @Schema(description = "名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "芋艿")
    @NotNull(message = "名称不能为空")
    private String name;

    @Schema(description = "字段名", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "字段名不能为空")
    private String field;

    @Schema(description = "验证器", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "验证器不能为空")
    private String validator;

    @Schema(description = "主表单", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "主表单不能为空")
    private Long formId;

    @Schema(description = "类型", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @NotNull(message = "类型不能为空")
    private String type;

    @Schema(description = "默认值", requiredMode = Schema.RequiredMode.REQUIRED)
    private String defaultValue;

    @Schema(description = "排序", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "排序不能为空")
    private Integer sort;

}
