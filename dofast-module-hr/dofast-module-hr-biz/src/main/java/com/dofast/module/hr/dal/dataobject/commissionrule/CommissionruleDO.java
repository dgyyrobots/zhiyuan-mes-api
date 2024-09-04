package com.dofast.module.hr.dal.dataobject.commissionrule;

import java.time.LocalDate;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import com.dofast.framework.mybatis.core.dataobject.BaseDO;

/**
 * 提成规则 DO
 *
 * @author 惠智造
 */
@TableName("hr_commissionrule")
@KeySequence("hr_commissionrule_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommissionruleDO extends BaseDO {

    /**
     * 提成规则id
     */
    @TableId
    private Integer id;
    /**
     * 月份
     */
    private String month;
    /**
     * 账户
     */
    private String account;
    /**
     * 薪资
     */
    private String sale;
    /**
     * 线
     */
    private String line;

}
