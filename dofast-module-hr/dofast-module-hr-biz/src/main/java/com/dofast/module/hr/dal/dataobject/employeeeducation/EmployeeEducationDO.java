package com.dofast.module.hr.dal.dataobject.employeeeducation;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import com.dofast.framework.mybatis.core.dataobject.BaseDO;

/**
 * 员工教育培训经历 DO
 *
 * @author 惠智造
 */
@TableName("hr_employee_education")
@KeySequence("hr_employee_education_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeEducationDO extends BaseDO {

    /**
     * 学校
     */
    private String educationSchool;
    /**
     * 专业
     */
    private String educationSpecialty;
    /**
     * 所获荣誉
     */
    private String educationHonor;
    /**
     * 教育开始时间
     */
    private LocalDateTime educationStarttime;
    /**
     * 教育结束时间
     */
    private LocalDateTime educationEndtime;
    /**
     * 培训教育经历id
     */
    @TableId
    private Long id;
    /**
     * 员工id
     */
    private Long employeeId;

}
