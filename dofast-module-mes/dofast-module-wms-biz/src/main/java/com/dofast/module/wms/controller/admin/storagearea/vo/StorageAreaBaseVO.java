package com.dofast.module.wms.controller.admin.storagearea.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.math.BigDecimal;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import javax.validation.constraints.*;

/**
 * 库位 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class StorageAreaBaseVO {

    @Schema(description = "库位编码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "库位编码不能为空")
    private String areaCode;

    @Schema(description = "库位名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "王五")
    @NotNull(message = "库位名称不能为空")
    private String areaName;

    @Schema(description = "库区ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "14951")
    @NotNull(message = "库区ID不能为空")
    private Long locationId;

    @Schema(description = "面积")
    private BigDecimal area;

    @Schema(description = "最大载重量")
    private BigDecimal maxLoa;

    @Schema(description = "库位位置X")
    private Integer positionX;

    @Schema(description = "库位位置y")
    private Integer positionY;

    @Schema(description = "库位位置z")
    private Integer positionZ;

    @Schema(description = "是否启用")
    private String enableFlag;

    @Schema(description = "备注", example = "你说的对")
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
