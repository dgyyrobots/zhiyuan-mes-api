package com.dofast.module.mes.controller.admin.mdunitmeasure.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import javax.validation.constraints.*;

/**
 * 单位 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class MdUnitMeasureBaseVO {

    @Schema(description = "单位编码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "单位编码不能为空")
    private String measureCode;

    @Schema(description = "单位名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "王五")
    @NotNull(message = "单位名称不能为空")
    private String measureName;

    @Schema(description = "是否是主单位", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "是否是主单位不能为空")
    private String primaryFlag;

    @Schema(description = "主单位ID", example = "18490")
    private Long primaryId;

    @Schema(description = "与主单位换算比例")
    private BigDecimal changeRate;

    @Schema(description = "是否启用", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "是否启用不能为空")
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
