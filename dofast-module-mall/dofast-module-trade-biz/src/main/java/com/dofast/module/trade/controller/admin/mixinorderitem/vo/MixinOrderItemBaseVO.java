package com.dofast.module.trade.controller.admin.mixinorderitem.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * 销售的物料明细 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class MixinOrderItemBaseVO {

    @Schema(description = "产品物料ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "26593")
    @NotNull(message = "产品物料ID不能为空")
    private Long itemId;

    @Schema(description = "产品物料编码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "产品物料编码不能为空")
    private String itemCode;

    @Schema(description = "产品物料名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "李四")
    @NotNull(message = "产品物料名称不能为空")
    private String itemName;

    @Schema(description = "销售订单ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "5322")
    @NotNull(message = "销售订单ID不能为空")
    private Long saleId;

    @Schema(description = "销售单编码")
    private String saleNo;

    @Schema(description = "标题", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "标题不能为空")
    private String title;

    @Schema(description = "单价", requiredMode = Schema.RequiredMode.REQUIRED, example = "17831")
    @NotNull(message = "单价不能为空")
    private BigDecimal singlePrice;

    @Schema(description = "数量", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "数量不能为空")
    private Integer num;

    @Schema(description = "总价", requiredMode = Schema.RequiredMode.REQUIRED, example = "17830")
    @NotNull(message = "总价不能为空")
    private BigDecimal totalPrice;

}
