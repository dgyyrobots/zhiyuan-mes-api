package com.dofast.module.mes.controller.admin.Autocoderule.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import javax.validation.constraints.*;

/**
 * 编码生成规则 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class AutoCodeRuleBaseVO {

    @Schema(description = "规则编码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "规则编码不能为空")
    private String ruleCode;

    @Schema(description = "规则名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "李四")
    @NotNull(message = "规则名称不能为空")
    private String ruleName;

    @Schema(description = "描述")
    private String ruleDesc;

    @Schema(description = "最大长度")
    private Integer maxLength;

    @Schema(description = "是否补齐", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "是否补齐不能为空")
    private String isPadded;

    @Schema(description = "补齐字符")
    private String paddedChar;

    @Schema(description = "补齐方式")
    private String paddedMethod;

    @Schema(description = "是否启用", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "是否启用不能为空")
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
