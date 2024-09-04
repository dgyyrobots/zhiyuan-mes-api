package com.dofast.module.finance.controller.admin.bpm.cashTrade.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static com.dofast.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

/**
 * 财务流水 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class CashTradeBpmBaseVO {

    @Schema(description = "流程实例的编号")
    private String processInstanceId;

    @Schema(description = "供应者", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "供应者不能为空")
    private Long depositor;

    @Schema(description = "父账号", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "父账号不能为空")
    private Long parent;

    @Schema(description = "产品")
//    @NotNull(message = "产品不能为空")
    private Long product;

    @Schema(description = "交易人", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "交易人不能为空")
    private Long trader;

    @Schema(description = "订单", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "订单不能为空")
    private Long orderId;

    @Schema(description = "合同", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "合同不能为空")
    private Long contract;

    @Schema(description = "关联类型", example = "2")
//    @NotNull(message = "关联类型不能为空")
    private String relatedType;

    @Schema(description = "关联id", example = "30117")
//    @NotNull(message = "关联id不能为空")
    private Long relatedId;

    @Schema(description = "项目")
//    @NotNull(message = "项目不能为空")
    private Long project;

    @Schema(description = "投资", example = "23084")
//    @NotNull(message = "投资不能为空")
    private Long investId;

    @Schema(description = "贷款", example = "5729")
//    @NotNull(message = "贷款不能为空")
    private Long loanId;

    @Schema(description = "转账", example = "7289")
//    @NotNull(message = "转账不能为空")
    private Long transferId;

    @Schema(description = "部门", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "部门不能为空")
    private Long dept;

    @Schema(description = "类型", requiredMode = Schema.RequiredMode.REQUIRED, example = "in")
    @NotNull(message = "类型不能为空")
    private String type;

    @Schema(description = "金额", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "金额不能为空")
    private BigDecimal money;

    @Schema(description = "汇率")
//    @NotNull(message = "汇率不能为空")
    private BigDecimal exchangeRate;

    @Schema(description = "当前额度", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "当前额度不能为空")
    private String currency;

    @Schema(description = "日期", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "日期不能为空")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime date;

    @Schema(description = "时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "时间不能为空")
    private LocalTime time;

    @Schema(description = "最后期限", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "最后期限不能为空")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime deadline;

    @Schema(description = "操作人", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "操作人不能为空")
    private String handlers;

    @Schema(description = "分类", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "分类不能为空")
    private String category;

    @Schema(description = "结果")
    private Integer isReturn;

}
