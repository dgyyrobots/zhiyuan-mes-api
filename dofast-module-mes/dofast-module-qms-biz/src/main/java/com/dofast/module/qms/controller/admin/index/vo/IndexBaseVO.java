package com.dofast.module.qms.controller.admin.index.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import javax.validation.constraints.*;

/**
 * 检测项 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class IndexBaseVO {

    @Schema(description = "检测项编码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "检测项编码不能为空")
    private String indexCode;

    @Schema(description = "检测项名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "王五")
    @NotNull(message = "检测项名称不能为空")
    private String indexName;

    @Schema(description = "检测项类型", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "检测项类型不能为空")
    private String indexType;

    @Schema(description = "检测工具")
    private String qcTool;

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
