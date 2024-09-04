package com.dofast.module.mes.controller.admin.autocoderesult.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import javax.validation.constraints.*;

/**
 * 编码生成记录 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class AutoCodeResultBaseVO {

    @Schema(description = "规则ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "6039")
    @NotNull(message = "规则ID不能为空")
    private Long ruleId;

    @Schema(description = "生成日期时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "生成日期时间不能为空")
    private String genDate;

    @Schema(description = "最后产生的序号")
    private Integer genIndex;

    @Schema(description = "最后产生的值")
    private String lastResult;

    @Schema(description = "最后产生的流水号")
    private Integer lastSerialNo;

    @Schema(description = "最后传入的参数")
    private String lastInputChar;

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

}
