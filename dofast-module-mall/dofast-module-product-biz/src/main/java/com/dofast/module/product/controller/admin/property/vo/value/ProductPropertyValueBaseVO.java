package com.dofast.module.product.controller.admin.property.vo.value;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
* 属性值 Base VO，提供给添加、修改、详细的子 VO 使用
* 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
*/
@Data
public class ProductPropertyValueBaseVO {

    @Schema(description = "属性项的编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "1024")
    @NotNull(message = "属性项的编号不能为空")
    private Long propertyId;

    @Schema(description = "名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "红色")
    @NotEmpty(message = "名称名字不能为空")
    private String name;

    @Schema(description = "备注", example = "颜色")
    private String remark;

}
