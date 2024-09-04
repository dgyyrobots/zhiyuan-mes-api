package com.dofast.module.hr.dal.dataobject.employeeworkhistory;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import com.dofast.framework.mybatis.core.dataobject.BaseDO;

/**
 * 员工工作经历 DO
 *
 * @author 惠智造
 */
@TableName("hr_employee_workhistory")
@KeySequence("hr_employee_workhistory_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeWorkhistoryDO extends BaseDO {

    /**
     * 从业公司
     */
    private String companyName;
    /**
     * 从业薪资
     */
    private Integer treatment;
    /**
     * 担任职位
     */
    private String treatmentPost;
    /**
     * 离职原因
     */
    private String reasonForLeave;
    /**
     * id
     */
    @TableId
    private Long id;
    /**
     * 员工id
     */
    private Long employeeId;

}
