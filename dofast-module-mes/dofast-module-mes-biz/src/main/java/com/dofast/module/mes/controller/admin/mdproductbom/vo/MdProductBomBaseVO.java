package com.dofast.module.mes.controller.admin.mdproductbom.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import javax.validation.constraints.*;

/**
 * 产品BOM关系 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class MdProductBomBaseVO {

    @Schema(description = "物料产品ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "8195")
    @NotNull(message = "物料产品ID不能为空")
    private Long itemId;

    @Schema(description = "BOM物料ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "10443")
    @NotNull(message = "BOM物料ID不能为空")
    private Long bomItemId;

    @Schema(description = "BOM物料编码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "BOM物料编码不能为空")
    private String bomItemCode;

    @Schema(description = "BOM物料名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "王五")
    @NotNull(message = "BOM物料名称不能为空")
    private String bomItemName;

    @Schema(description = "BOM物料规格")
    private String bomItemSpec;

    @Schema(description = "BOM物料单位", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "BOM物料单位不能为空")
    private String unitOfMeasure;

    @Schema(description = "产品物料标识", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "产品物料标识不能为空")
    private String itemOrProduct;

    @Schema(description = "物料使用比例", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "物料使用比例不能为空")
    private Double quantity;

    @Schema(description = "是否启用", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "是否启用不能为空")
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

    public Long getId(){
        return null;
    }

}
