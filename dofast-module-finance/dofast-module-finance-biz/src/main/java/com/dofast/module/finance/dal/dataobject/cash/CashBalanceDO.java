package com.dofast.module.finance.dal.dataobject.cash;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import com.dofast.framework.mybatis.core.dataobject.BaseDO;

/**
 * 现金余额 DO
 *
 * @author 惠智造
 */
@TableName("cash_balance")
@KeySequence("cash_balance_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CashBalanceDO extends BaseDO {

    /**
     * 余额明细id
     */
    @TableId
    private Long id;
    /**
     * 资金账户
     */
    private Long depositor;
    /**
     * 时间
     */
    private LocalDateTime date;
    /**
     * 金额
     */
    private BigDecimal money;
    /**
     * 当前余额
     */
    private String currency;

}
