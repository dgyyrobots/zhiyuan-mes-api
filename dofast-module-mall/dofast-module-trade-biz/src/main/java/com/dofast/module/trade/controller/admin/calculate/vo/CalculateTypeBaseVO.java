package com.dofast.module.trade.controller.admin.calculate.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 计价类型 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class CalculateTypeBaseVO {

    @Schema(description = "名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "赵六")
    @NotNull(message = "名称不能为空")
    private String name;

    @Schema(description = "表单", requiredMode = Schema.RequiredMode.REQUIRED, example = "13786")
    @NotNull(message = "表单不能为空")
    private String formId;

    @Schema(description = "表达式类型", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @NotNull(message = "表达式类型不能为空")
    private String type;

    @Schema(description = "表达式", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "表达式不能为空")
    private String expression;

    @Schema(description = "状态", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @NotNull(message = "状态不能为空")
    private Integer status;

    @Schema(description = "排序", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "排序不能为空")
    private Integer sort;

}
