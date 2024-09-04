package com.dofast.module.finance.dal.dataobject.cash;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import com.dofast.framework.mybatis.core.dataobject.BaseDO;

/**
 * 业务发票关联 DO
 *
 * @author 惠智造
 */
@TableName("cash_trade_invoice")
@KeySequence("cash_trade_invoice_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CashTradeInvoiceDO extends BaseDO {

    /**
     * 渠道
     */
    @TableId
    private Long trade;
    /**
     * 发票
     */
    private Long invoice;

}
