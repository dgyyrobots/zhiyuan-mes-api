package com.dofast.module.trade.controller.admin.order.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 采集任务类型 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class OrderCollectionTypeBaseVO {

    @Schema(description = "名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "李四")
    @NotNull(message = "名称不能为空")
    private String name;

    @Schema(description = "表单", requiredMode = Schema.RequiredMode.REQUIRED, example = "32737")
    @NotNull(message = "表单不能为空")
    private Long formId;

    @Schema(description = "状态", requiredMode = Schema.RequiredMode.REQUIRED, example = "0")
    @NotNull(message = "状态不能为空")
    private Integer status;

    @Schema(description = "类型", requiredMode = Schema.RequiredMode.REQUIRED, example = "0")
    @NotNull(message = "类型不能为空")
    private Integer type;

    @Schema(description = "描述", example = "随便")
    private String description;


    @Schema(description = "序号", requiredMode = Schema.RequiredMode.NOT_REQUIRED, example = "0")
    private Integer sortCode;

}
