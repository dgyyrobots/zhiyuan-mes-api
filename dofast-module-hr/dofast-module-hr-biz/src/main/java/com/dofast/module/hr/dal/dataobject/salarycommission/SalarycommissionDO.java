package com.dofast.module.hr.dal.dataobject.salarycommission;

import java.time.LocalDate;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import com.dofast.framework.mybatis.core.dataobject.BaseDO;

/**
 * 绩效工资 DO
 *
 * @author 惠智造
 */
@TableName("hr_salarycommission")
@KeySequence("hr_salarycommission_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SalarycommissionDO extends BaseDO {

    /**
     * 绩效工资id
     */
    @TableId
    private Integer id;
    /**
     * 工资
     */
    private Integer salary;
    /**
     * 绩效类型
     *
     * 枚举 {@link TODO hr_salary_type 对应的类}
     */
    private String type;
    /**
     * 绩效标准
     */
    private String line;
    /**
     * 绩效金额
     */
    private BigDecimal amount;
    /**
     * 比例
     */
    private BigDecimal rate;
    /**
     * 绩效
     */
    private BigDecimal commission;
    /**
     * 描述
     */
    private String desc;

}
