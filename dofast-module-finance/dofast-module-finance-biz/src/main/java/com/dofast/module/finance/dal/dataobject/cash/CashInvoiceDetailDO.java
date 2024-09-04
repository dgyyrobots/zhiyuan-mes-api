package com.dofast.module.finance.dal.dataobject.cash;

import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import com.dofast.framework.mybatis.core.dataobject.BaseDO;

/**
 * 财务发票明细 DO
 *
 * @author 惠智造
 */
@TableName("cash_invoice_detail")
@KeySequence("cash_invoice_detail_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CashInvoiceDetailDO extends BaseDO {

    /**
     * 发票明细id
     */
    @TableId
    private Long id;

    /**
     * 流程实例的编号
     */
    private String processInstanceId;

    /**
     * 开票信息id
     */
    private Long invoice;
    /**
     * 项目
     */
    private String item;
    /**
     * 项目类型
     */
    private String itemType;
    /**
     * 项目id
     */
    private Long itemId;
    /**
     * 模型
     */
    private String model;
    /**
     * 单位
     */
    private String unit;
    /**
     * 数量
     */
    private BigDecimal amount;
    /**
     * 价格
     */
    private BigDecimal price;
    /**
     * 金额
     */
    private BigDecimal money;
    /**
     * 税率
     */
    private Byte taxRate;
    /**
     * 税金
     */
    private BigDecimal taxMoney;

    /**
     * 状态
     */
    private String status;

}
