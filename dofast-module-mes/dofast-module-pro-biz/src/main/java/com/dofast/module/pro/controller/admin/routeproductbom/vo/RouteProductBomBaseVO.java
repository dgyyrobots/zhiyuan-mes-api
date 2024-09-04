package com.dofast.module.pro.controller.admin.routeproductbom.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import javax.validation.constraints.*;

/**
 * 产品制程物料BOM Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class RouteProductBomBaseVO {

    @Schema(description = "工艺路线ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "2418")
    @NotNull(message = "工艺路线ID不能为空")
    private Long routeId;

    @Schema(description = "工序ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "2131")
    @NotNull(message = "工序ID不能为空")
    private Long processId;

    @Schema(description = "产品BOM中的唯一ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "697")
    @NotNull(message = "产品BOM中的唯一ID不能为空")
    private Long productId;

    @Schema(description = "产品物料ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "12147")
    @NotNull(message = "产品物料ID不能为空")
    private Long itemId;

    @Schema(description = "产品物料编码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "产品物料编码不能为空")
    private String itemCode;

    @Schema(description = "产品物料名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "赵六")
    @NotNull(message = "产品物料名称不能为空")
    private String itemName;

    @Schema(description = "规格型号")
    private String specification;

    @Schema(description = "单位", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "单位不能为空")
    private String unitOfMeasure;

    @Schema(description = "用料比例")
    private Double quantity;

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
