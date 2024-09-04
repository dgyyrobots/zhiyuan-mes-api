package com.dofast.module.cmms.controller.admin.dvmachinerytype.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.ToString;

@Schema(description = "管理后台 - 设备类型简单列表 Simple VO")
@Data
@ToString(callSuper = true)
public class DvMachineryTypeSimpleVO {
    @Schema(description = "设备类型ID", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Schema(description = "父ID", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long pid;

    @Schema(description = "设备类型名称", requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;
}
