package com.dofast.module.hr.controller.admin.salary.vo;

import java.time.LocalDate;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import javax.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

import static com.dofast.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

/**
 * 工资总 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class SalaryBaseVO {

    @Schema(description = "订单流程编码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "订单流程编码不能为空")
    private String orderFlowNo;

    @Schema(description = "明细编码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "明细编码不能为空")
    private String detailNo;

    @Schema(description = "sn", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "sn不能为空")
    private String sn;

    @Schema(description = "月份", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "月份不能为空")
    private String month;

    @Schema(description = "账户", requiredMode = Schema.RequiredMode.REQUIRED, example = "15957")
    @NotNull(message = "账户不能为空")
    private String account;

    @Schema(description = "所属公司", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "所属公司不能为空")
    private Integer company;

    @Schema(description = "部门", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "部门不能为空")
    private Integer dept;

    @Schema(description = "基本工资", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "基本工资不能为空")
    private BigDecimal basic;

    @Schema(description = "绩效", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "绩效不能为空")
    private BigDecimal performance;

    @Schema(description = "红利", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "红利不能为空")
    private BigDecimal bonus;

    @Schema(description = "津贴", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "津贴不能为空")
    private BigDecimal allowance;

    @Schema(description = "免税金额", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "免税金额不能为空")
    private BigDecimal exemption;

    @Schema(description = "扣减", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "扣减不能为空")
    private BigDecimal deduction;

    @Schema(description = "奖励", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "奖励不能为空")
    private BigDecimal deserved;

    @Schema(description = "实际收入", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "实际收入不能为空")
    private BigDecimal actual;

    @Schema(description = "公司SSF", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "公司SSF不能为空")
    private BigDecimal companySsf;

    @Schema(description = "公司HPF", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "公司HPF不能为空")
    private BigDecimal companyHpf;

    @Schema(description = "可纳税所得额", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "可纳税所得额不能为空")
    private BigDecimal curTaxableIncome;

    @Schema(description = "应纳税收入", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "应纳税收入不能为空")
    private BigDecimal taxableIncome;

    @Schema(description = "应付税款", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "应付税款不能为空")
    private BigDecimal taxPayable;

    @Schema(description = "已纳税", requiredMode = Schema.RequiredMode.REQUIRED, example = "4905")
    @NotNull(message = "已纳税不能为空")
    private BigDecimal taxPaid;

    @Schema(description = "税额", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "税额不能为空")
    private BigDecimal tax;

    @Schema(description = "审核人")
    private String confirmer;

    @Schema(description = "审核时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime confirmTime;

    @Schema(description = "授权人")
    private String granter;

    @Schema(description = "授权时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime grantTime;

    @Schema(description = "描述", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "描述不能为空")
    private String desc;

    @Schema(description = "状态", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @NotNull(message = "状态不能为空")
    private String status;

}
