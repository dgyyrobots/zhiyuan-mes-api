package com.dofast.module.cmms.controller.admin.dvchecksubject.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import javax.validation.constraints.*;

/**
 * 点检项目 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class DvCheckSubjectBaseVO {

    @Schema(description = "计划ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "5302")
    @NotNull(message = "计划ID不能为空")
    private Long planId;

    @Schema(description = "项目ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "3613")
    @NotNull(message = "项目ID不能为空")
    private Long subjectId;

    @Schema(description = "项目编码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "项目编码不能为空")
    private String subjectCode;

    @Schema(description = "项目名称", example = "李四")
    private String subjectName;

    @Schema(description = "项目类型", example = "2")
    private String subjectType;

    @Schema(description = "项目内容", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "项目内容不能为空")
    private String subjectContent;

    @Schema(description = "标准")
    private String subjectStandard;

    @Schema(description = "备注", example = "你说的对")
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
