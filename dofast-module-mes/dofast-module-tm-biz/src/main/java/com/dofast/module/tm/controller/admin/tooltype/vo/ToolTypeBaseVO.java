package com.dofast.module.tm.controller.admin.tooltype.vo;

import java.time.LocalDate;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import javax.validation.constraints.*;

/**
 * 工装夹具类型 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class ToolTypeBaseVO {

    @Schema(description = "类型编码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "类型编码不能为空")
    private String toolTypeCode;

    @Schema(description = "类型名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "张三")
    @NotNull(message = "类型名称不能为空")
    private String toolTypeName;

    @Schema(description = "是否编码管理", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "是否编码管理不能为空")
    private String codeFlag;

    @Schema(description = "保养维护类型", example = "1")
    private String maintenType;

    @Schema(description = "保养周期")
    private Integer maintenPeriod;

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

}
