package com.dofast.module.finance.controller.admin.cash.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.alibaba.excel.annotation.ExcelProperty;
import com.dofast.framework.excel.core.annotations.DictFormat;
import com.dofast.framework.excel.core.convert.DictConvert;


/**
 * 发票信息 Excel VO
 *
 * @author 惠智造
 */
@Data
public class CashInvoiceExcelVO {

    @ExcelProperty("发票信息id")
    private Long id;

    @Schema(description = "流程实例的编号")
    private String processInstanceId;

    @ExcelProperty("系列编码")
    private Short serialNumber;

    @ExcelProperty("编码")
    private String sn;

    @ExcelProperty("所属公司")
    private Long company;

    @ExcelProperty("客户名称")
    private Long customer;

    @ExcelProperty("合同名称")
    private Long contract;

    @ExcelProperty("联系人")
    private Long contact;

    @ExcelProperty("联系地址")
    private Long address;

    @ExcelProperty("金额")
    private BigDecimal money;

    @ExcelProperty(value = "类别", converter = DictConvert.class)
    @DictFormat("cash_invoice_kind") // TODO 代码优化：建议设置到对应的 XXXDictTypeConstants 枚举类中
    private String kind;

    @ExcelProperty("种类")
    private String type;

    @ExcelProperty("销售类型")
    private String saleType;

    @ExcelProperty("税率")
    private Byte taxRate;

    @ExcelProperty("发票抬头")
    private String invoiceTitle;

    @ExcelProperty("税号")
    private String taxNumber;

    @ExcelProperty("注册地址")
    private String registedAddress;

    @ExcelProperty("电话")
    private String phone;

    @ExcelProperty("开户行")
    private String bankName;

    @ExcelProperty("银行账户")
    private String bankAccount;

    @ExcelProperty("快递名称")
    private String express;

    @ExcelProperty("快递单号")
    private String waybill;

    @ExcelProperty("发送方式")
    private String sendway;

    @ExcelProperty("发送账户")
    private String sendAccount;

    @ExcelProperty("状态")
    private String status;

    @ExcelProperty("子状态")
    private String subStatus;

    @ExcelProperty("描述")
    private String description;

    @ExcelProperty("收件人")
    private String receivedBy;

    @ExcelProperty("收件日期")
    private LocalDateTime receivedDate;

    @ExcelProperty("签收人")
    private String checkedBy;

    @ExcelProperty("签收日期")
    private LocalDateTime checkedDate;

    @ExcelProperty("打印经手人")
    private String drawnBy;

    @ExcelProperty("打印时间")
    private LocalDateTime drawnDate;

    @ExcelProperty("快递人")
    private String expressedBy;

    @ExcelProperty("快递日期")
    private LocalDateTime expressedDate;

    @ExcelProperty("发票退款人")
    private String taxRefundedBy;

    @ExcelProperty("发票退款日期")
    private LocalDateTime taxRefundedDate;

    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}
