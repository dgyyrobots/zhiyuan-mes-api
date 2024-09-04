package com.dofast.module.finance.dal.dataobject.cash;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import com.dofast.framework.mybatis.core.dataobject.BaseDO;

/**
 * 资金账号 DO
 *
 * @author 惠智造
 */
@TableName("cash_depositor")
@KeySequence("cash_depositor_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CashDepositorDO extends BaseDO {

    /**
     * 资金账户id
     */
    @TableId
    private Long id;
    /**
     * 账户类型
     *
     * 枚举 {@link TODO cash_depositor_type 对应的类}
     */
    private String type;
    /**
     * 简称
     */
    private String abbr;
    /**
     * 账户名称
     */
    private String title;
    /**
     * 账户标签
     */
    private String tags;
    /**
     * 供应者
     */
    private String provider;
    /**
     * 开户银行
     */
    private String bank;
    /**
     * 客户编码
     */
    private String customerNo;
    /**
     * 银行账号
     */
    private String account;
    /**
     * 联合银行编码
     */
    private String unionBankNo;
    /**
     * 清算银行编码
     */
    private String clearingBankNo;
    /**
     * 是否公开
     *
     * 枚举 {@link TODO infra_boolean_string 对应的类}
     */
    private String isPublic;
    /**
     * 当前余额
     */
    private String currency;
    /**
     * 状态
     *
     * 枚举 {@link TODO cash_depositor_status 对应的类}
     */
    private String status;

}
