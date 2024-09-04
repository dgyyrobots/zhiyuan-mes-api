package com.dofast.module.finance.controller.admin.cash.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import com.dofast.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.math.BigDecimal;

import static com.dofast.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 财务流水分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CashTradePageReqVO extends PageParam {

    @Schema(description = "供应者")
    private Long depositor;

    @Schema(description = "父账号")
    private Long parent;

    @Schema(description = "产品")
    private Long product;

    @Schema(description = "交易人")
    private Long trader;

    @Schema(description = "订单")
    private Long orderId;

    @Schema(description = "合同")
    private Long contract;

    @Schema(description = "关联类型", example = "2")
    private String relatedType;

    @Schema(description = "关联id", example = "30117")
    private Long relatedId;

    @Schema(description = "项目")
    private Long project;

    @Schema(description = "投资", example = "23084")
    private Long investId;

    @Schema(description = "贷款", example = "5729")
    private Long loanId;

    @Schema(description = "转账", example = "7289")
    private Long transferId;

    @Schema(description = "部门")
    private Long dept;

    @Schema(description = "类型", example = "in")
    private String type;

    @Schema(description = "金额")
    private BigDecimal money;

    @Schema(description = "汇率")
    private BigDecimal exchangeRate;

    @Schema(description = "当前额度")
    private String currency;

    @Schema(description = "日期")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] date;

    @Schema(description = "时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalTime[] time;

    @Schema(description = "最后期限")
    private LocalDateTime deadline;

    @Schema(description = "操作人")
    private String handlers;

    @Schema(description = "分类")
    private String category;

    @Schema(description = "结果")
    private Integer isReturn;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
