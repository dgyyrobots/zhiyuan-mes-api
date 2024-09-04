package com.dofast.module.finance.controller.admin.bpm.cashFund.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * 财务退款 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class CashFundBpmBaseVO {

    @Schema(description = "退款类型", requiredMode = Schema.RequiredMode.REQUIRED, example = "receivable")
    @NotNull(message = "退款类型不能为空")
    private String type;

    @Schema(description = "流程实例的编号")
    private String processInstanceId;

    @Schema(description = "退款原因", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "退款原因不能为空")
    private String origin;

    @Schema(description = "退款父id", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "退款父id不能为空")
    private Integer parent;

    @Schema(description = "业务", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "业务不能为空")
    private Long trader;

    @Schema(description = "合同", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "合同不能为空")
    private Long contract;

    @Schema(description = "订单", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "订单不能为空")
    private Long order;

    @Schema(description = "批次", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "批次不能为空")
    private Long batch;

    @Schema(description = "应退金额", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "应退金额不能为空")
    private BigDecimal deserved;

    @Schema(description = "实退金额", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "实退金额不能为空")
    private BigDecimal actual;

    @Schema(description = "余额", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "余额不能为空")
    private BigDecimal balance;

    @Schema(description = "客户", requiredMode = Schema.RequiredMode.REQUIRED, example = "0")
    @NotNull(message = "客户不能为空")
    private String custom;

    @Schema(description = "描述", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "描述不能为空")
    private String description;

    @Schema(description = "结果")
    private String isReturn;

}
