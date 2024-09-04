package com.dofast.module.system.controller.admin.systemconfig.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Schema(description = "批量创建配置参数 Request VO")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Value {
    @Schema(description = "配置项关键字", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "配置项关键字不能为空")
    private String configKey;

    @Schema(description = "配置值json")
    private String value;

    @Schema(description = "描述", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "描述不能为空")
    private String configDesc;

}
