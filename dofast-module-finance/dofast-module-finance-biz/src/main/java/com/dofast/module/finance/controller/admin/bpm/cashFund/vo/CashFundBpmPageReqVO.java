package com.dofast.module.finance.controller.admin.bpm.cashFund.vo;

import com.dofast.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static com.dofast.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 财务退款分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CashFundBpmPageReqVO extends PageParam {

    @Schema(description = "退款类型", example = "receivable")
    private String type;

    @Schema(description = "流程实例的编号")
    private String processInstanceId;

    @Schema(description = "退款原因")
    private String origin;

    @Schema(description = "退款父id")
    private Integer parent;

    @Schema(description = "业务")
    private Long trader;

    @Schema(description = "合同")
    private Long contract;

    @Schema(description = "订单")
    private Long order;

    @Schema(description = "批次")
    private Long batch;

    @Schema(description = "应退金额")
    private BigDecimal deserved;

    @Schema(description = "实退金额")
    private BigDecimal actual;

    @Schema(description = "余额")
    private BigDecimal balance;

    @Schema(description = "客户", example = "0")
    private String custom;

    @Schema(description = "描述")
    private String desc;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    @Schema(description = "结果")
    private String isReturn;

}
