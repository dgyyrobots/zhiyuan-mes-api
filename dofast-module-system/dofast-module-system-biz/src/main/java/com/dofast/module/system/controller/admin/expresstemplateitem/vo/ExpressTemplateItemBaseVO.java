package com.dofast.module.system.controller.admin.expresstemplateitem.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 运费模板细节 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class ExpressTemplateItemBaseVO {

    @Schema(description = "模板id", requiredMode = Schema.RequiredMode.REQUIRED, example = "10134")
    @NotNull(message = "模板id不能为空")
    private Long templateId;

    @Schema(description = "可配送地址id序列")
    private String areaIds;

    @Schema(description = "起步计算标准（首重，首件）", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "起步计算标准（首重，首件）不能为空")
    private Integer startUnit;

    @Schema(description = "起步计算价格，单位（分）", requiredMode = Schema.RequiredMode.REQUIRED, example = "25668")
    @NotNull(message = "起步计算价格，单位（分）不能为空")
    private Integer startUnitPrice;

    @Schema(description = "续步计算标准（每件，每kg）", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "续步计算标准（每件，每kg）不能为空")
    private Integer plusPerUnit;

    @Schema(description = "续步计算价格，单位（分）", requiredMode = Schema.RequiredMode.REQUIRED, example = "14371")
    @NotNull(message = "续步计算价格，单位（分）不能为空")
    private Integer plusPerUnitPrice;

    @Schema(description = "运费计算方式", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @NotNull(message = "运费计算方式不能为空")
    private Byte priceType;

}
