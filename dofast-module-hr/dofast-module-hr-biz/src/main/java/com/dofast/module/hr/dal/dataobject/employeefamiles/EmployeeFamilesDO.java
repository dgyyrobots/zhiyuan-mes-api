package com.dofast.module.hr.dal.dataobject.employeefamiles;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import com.dofast.framework.mybatis.core.dataobject.BaseDO;

/**
 * 员工家庭成员 DO
 *
 * @author 惠智造
 */
@TableName("hr_employee_familes")
@KeySequence("hr_employee_familes_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeFamilesDO extends BaseDO {

    /**
     * 家庭成员姓名
     */
    private String familesName;
    /**
     * 家庭成员关系
     */
    private String familesRealtion;
    /**
     * 家庭成员工作单位
     */
    private String familesWorkunit;
    /**
     * 家庭成员工作地区
     */
    private String familesWorkplace;
    /**
     * 家庭成员电话
     */
    private String familesPhone;
    /**
     * 家庭成员id
     */
    @TableId
    private Long id;
    /**
     * 员工id
     */
    private Long employeeId;

}
