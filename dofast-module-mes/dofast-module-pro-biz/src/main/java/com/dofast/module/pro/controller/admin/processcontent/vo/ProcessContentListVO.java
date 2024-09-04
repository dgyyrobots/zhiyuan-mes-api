package com.dofast.module.pro.controller.admin.processcontent.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ProcessContentListVO {
    @Schema(description = "内容ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "30366")
    private Long id;

    @Schema(description = "工序ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "9403")
    private Long processId;

    @Schema(description = "顺序编号")
    private Integer orderNum;

    @Schema(description = "内容说明")
    private String contentText;

    @Schema(description = "辅助设备")
    private String device;

    @Schema(description = "辅助材料")
    private String material;

    @Schema(description = "材料URL", example = "https://www.iocoder.cn")
    private String docUrl;

    @Schema(description = "备注", example = "随便")
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
