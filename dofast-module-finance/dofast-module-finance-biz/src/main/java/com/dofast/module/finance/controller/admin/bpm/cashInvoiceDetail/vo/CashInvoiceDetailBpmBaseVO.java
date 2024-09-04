package com.dofast.module.finance.controller.admin.bpm.cashInvoiceDetail.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * 财务发票明细 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class CashInvoiceDetailBpmBaseVO {

    @Schema(description = "开票信息id", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "开票信息id不能为空")
    private Long invoice;

    @Schema(description = "流程实例的编号")
    private String processInstanceId;

    @Schema(description = "项目", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "项目不能为空")
    private String item;

    @Schema(description = "项目类型", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "项目类型不能为空")
    private String itemType;

    @Schema(description = "项目id", requiredMode = Schema.RequiredMode.REQUIRED, example = "28685")
    @NotNull(message = "项目id不能为空")
    private Long itemId;

    @Schema(description = "模型", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "模型不能为空")
    private String model;

    @Schema(description = "单位", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "单位不能为空")
    private String unit;

    @Schema(description = "数量", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "数量不能为空")
    private BigDecimal amount;

    @Schema(description = "价格", requiredMode = Schema.RequiredMode.REQUIRED, example = "30035")
    @NotNull(message = "价格不能为空")
    private BigDecimal price;

    @Schema(description = "金额", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "金额不能为空")
    private BigDecimal money;

    @Schema(description = "税率", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "税率不能为空")
    private Byte taxRate;

    @Schema(description = "税金", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "税金不能为空")
    private BigDecimal taxMoney;

    @Schema(description = "状态")
    private String status;

}
