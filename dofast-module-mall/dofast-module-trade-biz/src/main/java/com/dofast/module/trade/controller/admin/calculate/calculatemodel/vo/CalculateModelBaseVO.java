package com.dofast.module.trade.controller.admin.calculate.calculatemodel.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 计价模型 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class CalculateModelBaseVO {

    @Schema(description = "名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "王五")
    @NotNull(message = "名称不能为空")
    private String name;

    @Schema(description = "备注", example = "你猜")
    private String description;

    @Schema(description = "计价类型id", requiredMode = Schema.RequiredMode.REQUIRED, example = "19639")
    @NotNull(message = "计价类型id不能为空")
    private Integer categoryId;

    @Schema(description = "表单", requiredMode = Schema.RequiredMode.REQUIRED, example = "25593")
    @NotNull(message = "表单不能为空")
    private Long formId;

    @Schema(description = "表达式类型", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "表达式类型不能为空")
    private String type;

    @Schema(description = "表达式", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "表达式不能为空")
    private String expression;

    @Schema(description = "状态", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "状态不能为空")
    private Integer status;

    @Schema(description = "排序", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "排序不能为空")
    private Integer sort;

}
