package com.dofast.module.finance.dal.dataobject.cash;

import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import com.dofast.framework.mybatis.core.dataobject.BaseDO;

/**
 * 财务退款 DO
 *
 * @author 惠智造
 */
@TableName("cash_fund")
@KeySequence("cash_fund_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CashFundDO extends BaseDO {

    /**
     * 退款明细id
     */
    @TableId
    private Long id;

    /**
     * 流程实例的编号
     */
    private String processInstanceId;

    /**
     * 退款类型
     *
     * 枚举 {@link TODO cash_fund_type 对应的类}
     */
    private String type;
    /**
     * 退款原因
     */
    private String origin;
    /**
     * 退款父id
     */
    private Integer parent;
    /**
     * 业务
     */
    private Long trader;
    /**
     * 合同
     */
    private Long contract;
    /**
     * 订单
     */
    private Long order;
    /**
     * 批次
     */
    private Long batch;
    /**
     * 应退金额
     */
    private BigDecimal deserved;
    /**
     * 实退金额
     */
    private BigDecimal actual;
    /**
     * 余额
     */
    private BigDecimal balance;
    /**
     * 客户
     *
     * 枚举 {@link TODO infra_boolean_string 对应的类}
     */
    private String custom;
    /**
     * 描述
     */
    private String description;
    /**
     * 结果
     */
    private String isReturn;

}
