package com.dofast.module.pro.controller.admin.workorderbom.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import javax.validation.constraints.*;

/**
 * 生产工单BOM组成 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class WorkorderBomBaseVO {

    @Schema(description = "生产工单ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "7482")
    @NotNull(message = "生产工单ID不能为空")
    private Long workorderId;

    @Schema(description = "BOM物料ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "11673")
    @NotNull(message = "BOM物料ID不能为空")
    private Long itemId;

    @Schema(description = "BOM物料编号", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "BOM物料编号不能为空")
    private String itemCode;

    @Schema(description = "BOM物料名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "王五")
    @NotNull(message = "BOM物料名称不能为空")
    private String itemName;

    @Schema(description = "规格型号")
    private String itemSpc;

    @Schema(description = "单位", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "单位不能为空")
    private String unitOfMeasure;

    @Schema(description = "物料产品标识", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "物料产品标识不能为空")
    private String itemOrProduct;

    @Schema(description = "预计使用量", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "预计使用量不能为空")
    private Double quantity;

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

    @Schema(description = "项次")
    private Long sequence;

    @Schema(description = "项序")
    private Long sequenceOrder;

    public Long getId(){
        return null;
    }

}
