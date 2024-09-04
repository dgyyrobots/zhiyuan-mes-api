package com.dofast.module.finance.dal.dataobject.cash;

import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import com.dofast.framework.mybatis.core.dataobject.BaseDO;

/**
 * 发票信息 DO
 *
 * @author 惠智造
 */
@TableName("cash_invoice")
@KeySequence("cash_invoice_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CashInvoiceDO extends BaseDO {

    /**
     * 发票信息id
     */
    @TableId
    private Long id;

    /**
     * 流程实例的编号
     */
    private String processInstanceId;

    /**
     * 系列编码
     */
    private Short serialNumber;
    /**
     * 编码
     */
    private String sn;
    /**
     * 所属公司
     */
    private Long company;
    /**
     * 客户名称
     */
    private Long customer;
    /**
     * 合同名称
     */
    private Long contract;
    /**
     * 联系人
     */
    private Long contact;
    /**
     * 联系地址
     */
    private Long address;
    /**
     * 金额
     */
    private BigDecimal money;
    /**
     * 类别
     *
     * 枚举 {@link TODO cash_invoice_kind 对应的类}
     */
    private String kind;
    /**
     * 种类
     */
    private String type;
    /**
     * 销售类型
     */
    private String saleType;
    /**
     * 税率
     */
    private Byte taxRate;
    /**
     * 发票抬头
     */
    private String invoiceTitle;
    /**
     * 税号
     */
    private String taxNumber;
    /**
     * 注册地址
     */
    private String registedAddress;
    /**
     * 电话
     */
    private String phone;
    /**
     * 开户行
     */
    private String bankName;
    /**
     * 银行账户
     */
    private String bankAccount;
    /**
     * 快递名称
     */
    private String express;
    /**
     * 快递单号
     */
    private String waybill;
    /**
     * 发送方式
     */
    private String sendway;
    /**
     * 发送账户
     */
    private String sendAccount;
    /**
     * 状态
     */
    private String status;
    /**
     * 子状态
     */
    private String subStatus;
    /**
     * 描述
     */
    private String description;
    /**
     * 收件人
     */
    private String receivedBy;
    /**
     * 收件日期
     */
    private LocalDateTime receivedDate;
    /**
     * 签收人
     */
    private String checkedBy;
    /**
     * 签收日期
     */
    private LocalDateTime checkedDate;
    /**
     * 打印经手人
     */
    private String drawnBy;
    /**
     * 打印时间
     */
    private LocalDateTime drawnDate;
    /**
     * 快递人
     */
    private String expressedBy;
    /**
     * 快递日期
     */
    private LocalDateTime expressedDate;
    /**
     * 发票退款人
     */
    private String taxRefundedBy;
    /**
     * 发票退款日期
     */
    private LocalDateTime taxRefundedDate;

}
