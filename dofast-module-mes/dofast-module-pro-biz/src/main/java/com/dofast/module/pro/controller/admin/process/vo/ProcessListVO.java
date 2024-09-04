package com.dofast.module.pro.controller.admin.process.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;
@Data
public class ProcessListVO {
    @Schema(description = "工序编码")
    private Long id;

    @Schema(description = "工序编码" )
    private String processCode;

    @Schema(description = "工序名称", example = "芋艿")
    private String processName;

    @Schema(description = "工艺要求")
    private String attention;

    @Schema(description = "是否启用", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "是否启用不能为空")
    private String enableFlag;

    @Schema(description = "备注", example = "你猜")
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
