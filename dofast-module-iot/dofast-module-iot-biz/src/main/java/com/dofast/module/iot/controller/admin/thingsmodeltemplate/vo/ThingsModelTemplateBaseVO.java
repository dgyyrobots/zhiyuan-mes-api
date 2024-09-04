package com.dofast.module.iot.controller.admin.thingsmodeltemplate.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import javax.validation.constraints.*;

/**
 * 物模型模板 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class ThingsModelTemplateBaseVO {

    @Schema(description = "物模型名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "芋艿")
    @NotNull(message = "物模型名称不能为空")
    private String templateName;

    @Schema(description = "标识符，产品下唯一", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "标识符，产品下唯一不能为空")
    private String identifier;

    @Schema(description = "模型类别（1-属性，2-功能，3-事件）", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @NotNull(message = "模型类别（1-属性，2-功能，3-事件）不能为空")
    private Boolean type;

    @Schema(description = "数据类型（integer、decimal、string、bool、array、enum）", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @NotNull(message = "数据类型（integer、decimal、string、bool、array、enum）不能为空")
    private String datatype;

    @Schema(description = "数据定义", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "数据定义不能为空")
    private String specs;

    @Schema(description = "是否系统通用（0-否，1-是）", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "是否系统通用（0-否，1-是）不能为空")
    private Boolean isSys;

    @Schema(description = "是否首页显示（0-否，1-是）", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "是否首页显示（0-否，1-是）不能为空")
    private Boolean isTop;

    @Schema(description = "是否实时监测（0-否，1-是）", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "是否实时监测（0-否，1-是）不能为空")
    private Boolean isMonitor;

    @Schema(description = "备注", example = "你说的对")
    private String remark;

}
