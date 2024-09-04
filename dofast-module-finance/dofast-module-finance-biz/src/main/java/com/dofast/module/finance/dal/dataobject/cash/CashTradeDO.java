package com.dofast.module.finance.dal.dataobject.cash;

import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;
import com.baomidou.mybatisplus.annotation.*;
import com.dofast.framework.mybatis.core.dataobject.BaseDO;

/**
 * 财务流水 DO
 *
 * @author 惠智造
 */
@TableName("cash_trade")
@KeySequence("cash_trade_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CashTradeDO extends BaseDO {

    /**
     * 资金明细id
     */
    @TableId
    private Long id;
    
    /**
     * 流程实例的编号
     */
    private String processInstanceId;

    /**
     * 供应者
     */
    private Long depositor;
    /**
     * 父账号
     */
    private Long parent;
    /**
     * 产品
     */
    private Long product;
    /**
     * 交易人
     */
    private Long trader;
    /**
     * 订单
     */
    private Long orderId;
    /**
     * 合同
     */
    private Long contract;
    /**
     * 关联类型
     */
    private String relatedType;
    /**
     * 关联id
     */
    private Long relatedId;
    /**
     * 项目
     */
    private Long project;
    /**
     * 投资
     */
    private Long investId;
    /**
     * 贷款
     */
    private Long loanId;
    /**
     * 转账
     */
    private Long transferId;
    /**
     * 部门
     */
    private Long dept;
    /**
     * 类型
     *
     * 枚举 {@link TODO cash_trade_type 对应的类}
     */
    private String type;
    /**
     * 金额
     */
    private BigDecimal money;
    /**
     * 汇率
     */
    private BigDecimal exchangeRate;
    /**
     * 当前额度
     */
    private String currency;
    /**
     * 日期
     */
    private LocalDateTime date;
    /**
     * 时间
     */
    private LocalTime time;
    /**
     * 最后期限
     */
    private LocalDateTime deadline;
    /**
     * 操作人
     */
    private String handlers;
    /**
     * 分类
     */
    private String category;
    /**
     * 结果
     */
    private Integer isReturn;

}
