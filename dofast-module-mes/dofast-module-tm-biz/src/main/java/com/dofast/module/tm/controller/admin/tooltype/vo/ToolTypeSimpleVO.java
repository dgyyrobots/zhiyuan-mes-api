package com.dofast.module.tm.controller.admin.tooltype.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Schema(description = "管理后台 - 工装夹具类型简单 Simple VO")
@Data
@ToString(callSuper = true)
public class ToolTypeSimpleVO {
    @Schema(description = "工装夹具类型ID", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Schema(description = "类型编码", requiredMode = Schema.RequiredMode.REQUIRED)
    private String toolTypeCode;

    @Schema(description = "类型名称", requiredMode = Schema.RequiredMode.REQUIRED)
    private String toolTypeName;
}
