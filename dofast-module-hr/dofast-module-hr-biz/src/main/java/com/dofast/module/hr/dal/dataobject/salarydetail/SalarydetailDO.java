package com.dofast.module.hr.dal.dataobject.salarydetail;

import java.time.LocalDate;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import com.dofast.framework.mybatis.core.dataobject.BaseDO;

/**
 * 工资明细 DO
 *
 * @author 惠智造
 */
@TableName("hr_salarydetail")
@KeySequence("hr_salarydetail_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SalarydetailDO extends BaseDO {

    /**
     * 工资明细id
     */
    @TableId
    private Integer id;
    /**
     * 工资
     */
    private Integer salary;
    /**
     * 科目
     *
     * 枚举 {@link TODO hr_salary_item 对应的类}
     */
    private String item;
    /**
     * 类型
     */
    private String type;
    /**
     * 金额
     */
    private BigDecimal amount;
    /**
     * 描述
     */
    private String desc;

}
