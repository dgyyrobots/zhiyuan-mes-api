package com.dofast.module.pro.controller.admin.routeproductbom.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class RouteProductBomListVO {
    @Schema(description = "记录ID", example = "2418")
    private Long id;
    @Schema(description = "工艺路线ID", example = "2418")
    private Long routeId;

    @Schema(description = "工序ID", example = "2131")
    private Long processId;

    @Schema(description = "产品BOM中的唯一ID",  example = "697")
    private Long productId;

    @Schema(description = "产品物料ID", example = "12147")
    private Long itemId;

    @Schema(description = "产品物料编码")
    private String itemCode;

    @Schema(description = "产品物料名称",  example = "赵六")
    private String itemName;

    @Schema(description = "规格型号")
    private String specification;

    @Schema(description = "单位")
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

}
