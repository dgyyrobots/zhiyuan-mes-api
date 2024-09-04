package com.dofast.module.finance.controller.admin.cash.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import javax.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

import static com.dofast.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

/**
 * 发票信息 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class CashInvoiceBaseVO {

    @Schema(description = "系列编码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "系列编码不能为空")
    private Short serialNumber;

    @Schema(description = "流程实例的编号")
    private String processInstanceId;

    @Schema(description = "编码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "编码不能为空")
    private String sn;

    @Schema(description = "所属公司", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "所属公司不能为空")
    private Long company;

    @Schema(description = "客户名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "客户名称不能为空")
    private Long customer;

    @Schema(description = "合同名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "合同名称不能为空")
    private Long contract;

    @Schema(description = "联系人", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "联系人不能为空")
    private Long contact;

    @Schema(description = "联系地址", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "联系地址不能为空")
    private Long address;

    @Schema(description = "金额", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "金额不能为空")
    private BigDecimal money;

    @Schema(description = "类别", requiredMode = Schema.RequiredMode.REQUIRED, example = "purchase")
    @NotNull(message = "类别不能为空")
    private String kind;

    @Schema(description = "种类", requiredMode = Schema.RequiredMode.REQUIRED, example = "0")
    @NotNull(message = "种类不能为空")
    private String type;

    @Schema(description = "销售类型", requiredMode = Schema.RequiredMode.REQUIRED, example = "0")
    @NotNull(message = "销售类型不能为空")
    private String saleType;

    @Schema(description = "税率", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "税率不能为空")
    private Byte taxRate;

    @Schema(description = "发票抬头", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "发票抬头不能为空")
    private String invoiceTitle;

    @Schema(description = "税号", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "税号不能为空")
    private String taxNumber;

    @Schema(description = "注册地址", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "注册地址不能为空")
    private String registedAddress;

    @Schema(description = "电话", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "电话不能为空")
    private String phone;

    @Schema(description = "开户行", requiredMode = Schema.RequiredMode.REQUIRED, example = "芋艿")
    @NotNull(message = "开户行不能为空")
    private String bankName;

    @Schema(description = "银行账户", requiredMode = Schema.RequiredMode.REQUIRED, example = "30406")
    @NotNull(message = "银行账户不能为空")
    private String bankAccount;

    @Schema(description = "快递名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "快递名称不能为空")
    private String express;

    @Schema(description = "快递单号", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "快递单号不能为空")
    private String waybill;

    @Schema(description = "发送方式", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "发送方式不能为空")
    private String sendway;

    @Schema(description = "发送账户", requiredMode = Schema.RequiredMode.REQUIRED, example = "2526")
    @NotNull(message = "发送账户不能为空")
    private String sendAccount;

    @Schema(description = "状态", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "状态不能为空")
    private String status;

    @Schema(description = "子状态", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @NotNull(message = "子状态不能为空")
    private String subStatus;

    @Schema(description = "描述", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "描述不能为空")
    private String description;

    @Schema(description = "收件人", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "收件人不能为空")
    private String receivedBy;

    @Schema(description = "收件日期", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "收件日期不能为空")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime receivedDate;

    @Schema(description = "签收人", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "签收人不能为空")
    private String checkedBy;

    @Schema(description = "签收日期", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "签收日期不能为空")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime checkedDate;

    @Schema(description = "打印经手人", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "打印经手人不能为空")
    private String drawnBy;

    @Schema(description = "打印时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "打印时间不能为空")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime drawnDate;

    @Schema(description = "快递人", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "快递人不能为空")
    private String expressedBy;

    @Schema(description = "快递日期", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "快递日期不能为空")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime expressedDate;

    @Schema(description = "发票退款人", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "发票退款人不能为空")
    private String taxRefundedBy;

    @Schema(description = "发票退款日期", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "发票退款日期不能为空")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime taxRefundedDate;

}
