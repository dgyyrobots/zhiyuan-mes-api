package com.dofast.module.system.controller.admin.systemconfig.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import javax.validation.constraints.*;

/**
 * 参数配置 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class SystemConfigBaseVO {

    @Schema(description = "应用端口关键字", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "应用端口关键字不能为空")
    private String appModule;

    @Schema(description = "配置项关键字", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "配置项关键字不能为空")
    private String configKey;

    @Schema(description = "配置值json")
    private String value;

    @Schema(description = "描述", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "描述不能为空")
    private String configDesc;

    @Schema(description = "是否启用 1启用 0不启用", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "是否启用 1启用 0不启用不能为空")
    private Byte isUse;

}
