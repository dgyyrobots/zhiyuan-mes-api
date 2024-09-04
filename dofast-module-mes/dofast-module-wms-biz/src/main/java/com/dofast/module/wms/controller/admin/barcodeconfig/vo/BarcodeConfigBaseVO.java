package com.dofast.module.wms.controller.admin.barcodeconfig.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import javax.validation.constraints.*;

/**
 * 条码配置 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class BarcodeConfigBaseVO {

    @Schema(description = "条码格式", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "条码格式不能为空")
    private String barcodeFormart;

    @Schema(description = "条码类型", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "条码类型不能为空")
    private String barcodeType;

    @Schema(description = "内容格式", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "内容格式不能为空")
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
