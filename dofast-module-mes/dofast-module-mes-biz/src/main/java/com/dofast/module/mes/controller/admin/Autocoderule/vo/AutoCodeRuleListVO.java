package com.dofast.module.mes.controller.admin.Autocoderule.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class AutoCodeRuleListVO extends AutoCodeRuleBaseVO {
    @Schema(description = "规则ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "19616")
    private Long id;

    @Schema(description = "规则编码")
    private String ruleCode;

    @Schema(description = "规则名称", example = "李四")
    private String ruleName;

    @Schema(description = "描述")
    private String ruleDesc;

    @Schema(description = "最大长度")
    private Integer maxLength;

    @Schema(description = "是否补齐")
    private String isPadded;

    @Schema(description = "补齐字符")
    private String paddedChar;

    @Schema(description = "补齐方式")
    private String paddedMethod;

    @Schema(description = "是否启用")
    private String enableFlag;

    @Schema(description = "备注", example = "你猜")
    private String remark;

    @Schema(description = "预留字段1")
    private String attr1;

    @Schema(description = "预留字段2")
    private String attr2;

    @Schema(description = "预留字段3")
    private Integer attr3;

    @Schema(description = "预留字段4")
    private Integer attr4;

}
