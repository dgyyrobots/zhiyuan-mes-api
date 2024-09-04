package com.dofast.module.wms.controller.admin.barcodeconfig.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class BarcodeConfigListVO {
    @Schema(description = "条码格式")
    private Long id;

    @Schema(description = "条码格式")
    private String barcodeFormart;

    @Schema(description = "条码类型", example = "1")
    private String barcodeType;

    @Schema(description = "内容格式")
    private String contentFormart;

    @Schema(description = "内容样例")
    private String contentExample;

    @Schema(description = "是否自动生成")
    private String autoGenFlag;

    @Schema(description = "默认的打印模板")
    private String defaultTemplate;

    @Schema(description = "是否生效")
    private String enableFlag;

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
