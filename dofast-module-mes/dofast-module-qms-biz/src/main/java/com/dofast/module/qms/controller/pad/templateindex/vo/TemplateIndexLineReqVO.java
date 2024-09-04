package com.dofast.module.qms.controller.pad.templateindex.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class TemplateIndexLineReqVO {

    @Schema(description = "物料ID")
    private Long itemId;

    @Schema(description = "质检类型")
    private String qcType;
}
