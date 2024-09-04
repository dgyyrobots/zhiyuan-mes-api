package com.dofast.module.finance.controller.admin.cash.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import com.dofast.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import java.math.BigDecimal;

import static com.dofast.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 发票信息分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CashInvoicePageReqVO extends PageParam {

    @Schema(description = "系列编码")
    private Short serialNumber;

    @Schema(description = "流程实例的编号")
    private String processInstanceId;

    @Schema(description = "编码")
    private String sn;

    @Schema(description = "所属公司")
    private Long company;

    @Schema(description = "客户名称")
    private Long customer;

    @Schema(description = "合同名称")
    private Long contract;

    @Schema(description = "联系人")
    private Long contact;

    @Schema(description = "联系地址")
    private Long address;

    @Schema(description = "金额")
    private BigDecimal money;

    @Schema(description = "类别", example = "purchase")
    private String kind;

    @Schema(description = "种类", example = "0")
    private String type;

    @Schema(description = "销售类型", example = "0")
    private String saleType;

    @Schema(description = "税率")
    private Byte taxRate;

    @Schema(description = "发票抬头")
    private String invoiceTitle;

    @Schema(description = "税号")
    private String taxNumber;

    @Schema(description = "注册地址")
    private String registedAddress;

    @Schema(description = "电话")
    private String phone;

    @Schema(description = "开户行", example = "芋艿")
    private String bankName;

    @Schema(description = "银行账户", example = "30406")
    private String bankAccount;

    @Schema(description = "快递名称")
    private String express;

    @Schema(description = "快递单号")
    private String waybill;

    @Schema(description = "发送方式")
    private String sendway;

    @Schema(description = "发送账户", example = "2526")
    private String sendAccount;

    @Schema(description = "状态", example = "1")
    private String status;

    @Schema(description = "子状态", example = "2")
    private String subStatus;

    @Schema(description = "描述")
    private String description;

    @Schema(description = "收件人")
    private String receivedBy;

    @Schema(description = "收件日期")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] receivedDate;

    @Schema(description = "签收人")
    private String checkedBy;

    @Schema(description = "签收日期")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] checkedDate;

    @Schema(description = "打印经手人")
    private String drawnBy;

    @Schema(description = "打印时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] drawnDate;

    @Schema(description = "快递人")
    private String expressedBy;

    @Schema(description = "快递日期")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] expressedDate;

    @Schema(description = "发票退款人")
    private String taxRefundedBy;

    @Schema(description = "发票退款日期")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] taxRefundedDate;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
