package com.dofast.module.mes.controller.admin.Autocodepart.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import javax.validation.constraints.*;

/**
 * 编码生成规则组成 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class AutoCodePartBaseVO {

    @Schema(description = "规则ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "22734")
    @NotNull(message = "规则ID不能为空")
    private Long ruleId;

    @Schema(description = "分段序号", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "分段序号不能为空")
    private Integer partIndex;

    @Schema(description = "分段类型，INPUTCHAR：输入字符，NOWDATE：当前日期时间，FIXCHAR：固定字符，SERIALNO：流水号", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "分段类型，INPUTCHAR：输入字符，NOWDATE：当前日期时间，FIXCHAR：固定字符，SERIALNO：流水号不能为空")
    private String partType;

    @Schema(description = "分段编号")
    private String partCode;

    @Schema(description = "分段名称", example = "李四")
    private String partName;

    @Schema(description = "分段长度", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "分段长度不能为空")
    private Integer partLength;

    @Schema(description = "格式化")
    private String dateFormat;

    @Schema(description = "输入字符")
    private String inputCharacter;

    @Schema(description = "固定字符")
    private String fixCharacter;

    @Schema(description = "流水号起始值")
    private Integer seriaStartNo;

    @Schema(description = "流水号步长")
    private Integer seriaStep;

    @Schema(description = "流水号当前值")
    private Integer seriaNowNo;

    @Schema(description = "流水号是否循环")
    private String cycleFlag;

    @Schema(description = "循环方式，YEAR：按年，MONTH：按月，DAY：按天，HOUR：按小时，MINITE：按分钟，OTHER：按传入字符变")
    private String cycleMethod;

    @Schema(description = "备注", example = "随便")
    private String remark;

    @Schema(description = "预留字段1")
    private String attr1;

    @Schema(description = "预留字段2")
    private String attr2;

    @Schema(description = "预留字段3")
    private Integer attr3;

    @Schema(description = "预留字段4")
    private Integer attr4;

    public Long getId(){
        return null;
    }

}
