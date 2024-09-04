package com.dofast.module.wms.controller.admin.barcode.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import javax.validation.constraints.*;

/**
 * 条码清单 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class BarcodeBaseVO {

    @Schema(description = "条码格式", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "条码格式不能为空")
    private String barcodeFormart;

    @Schema(description = "条码类型", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "条码类型不能为空")
    private String barcodeType;

    @Schema(description = "条码内容", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "条码内容不能为空")
    private String barcodeContent;

    @Schema(description = "业务ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "30943")
    @NotNull(message = "业务ID不能为空")
    private Long bussinessId;

    @Schema(description = "业务编码")
    private String bussinessCode;

    @Schema(description = "业务名称", example = "王五")
    private String bussinessName;

    @Schema(description = "条码地址", example = "https://www.iocoder.cn")
    private String barcodeUrl;

    @Schema(description = "是否生效")
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

    public Long getId(){return null;}
}
