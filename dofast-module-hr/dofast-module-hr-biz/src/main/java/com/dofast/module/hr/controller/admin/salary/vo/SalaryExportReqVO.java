package com.dofast.module.hr.controller.admin.salary.vo;

import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import com.dofast.framework.common.pojo.PageParam;
import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;

import static com.dofast.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 工资总 Excel 导出 Request VO，参数和 SalaryPageReqVO 是一致的")
@Data
public class SalaryExportReqVO {

    @Schema(description = "订单流程编码")
    private String orderFlowNo;

    @Schema(description = "明细编码")
    private String detailNo;

    @Schema(description = "sn")
    private String sn;

    @Schema(description = "月份")
    private String month;

    @Schema(description = "账户", example = "15957")
    private String account;

    @Schema(description = "所属公司")
    private Integer company;

    @Schema(description = "部门")
    private Integer dept;

    @Schema(description = "基本工资")
    private BigDecimal basic;

    @Schema(description = "绩效")
    private BigDecimal performance;

    @Schema(description = "红利")
    private BigDecimal bonus;

    @Schema(description = "津贴")
    private BigDecimal allowance;

    @Schema(description = "免税金额")
    private BigDecimal exemption;

    @Schema(description = "扣减")
    private BigDecimal deduction;

    @Schema(description = "奖励")
    private BigDecimal deserved;

    @Schema(description = "实际收入")
    private BigDecimal actual;

    @Schema(description = "公司SSF")
    private BigDecimal companySsf;

    @Schema(description = "公司HPF")
    private BigDecimal companyHpf;

    @Schema(description = "可纳税所得额")
    private BigDecimal curTaxableIncome;

    @Schema(description = "应纳税收入")
    private BigDecimal taxableIncome;

    @Schema(description = "应付税款")
    private BigDecimal taxPayable;

    @Schema(description = "已纳税", example = "4905")
    private BigDecimal taxPaid;

    @Schema(description = "税额")
    private BigDecimal tax;

    @Schema(description = "审核人")
    private String confirmer;

    @Schema(description = "审核时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] confirmTime;

    @Schema(description = "授权人")
    private String granter;

    @Schema(description = "授权时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] grantTime;

    @Schema(description = "描述")
    private String desc;

    @Schema(description = "状态", example = "2")
    private String status;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
