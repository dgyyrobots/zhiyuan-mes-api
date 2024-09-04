package com.dofast.module.hr.enums;

import com.dofast.framework.common.exception.ErrorCode;

/**
 */
public interface ErrorCodeConstants {

    // === 提成规则 1020000000 ===
    ErrorCode COMMISSIONRULE_NOT_EXISTS = new ErrorCode(1020001000, "提成规则不存在");
    
    // === 员工教育培训经历 1020002000 ===
    ErrorCode EMPLOYEE_EDUCATION_NOT_EXISTS = new ErrorCode(1020002000, "员工教育培训经历不存在");
    ErrorCode EMPLOYEE_EDUCATION_NO_PERMISSION = new ErrorCode(1020002001, "没有修改权限");
    
    // === 员工家庭成员 1020003000 ===
    ErrorCode EMPLOYEE_FAMILES_NOT_EXISTS = new ErrorCode(1020003000, "员工家庭成员不存在");
    ErrorCode EMPLOYEE_FAMILES_NO_PERMISSION = new ErrorCode(1020003001, "没有修改权限");
    
    // === 员工信息文件 1020004000 ===
    ErrorCode EMPLOYEE_FILE_NOT_EXISTS = new ErrorCode(1020004000, "员工信息文件不存在");
    ErrorCode EMPLOYEE_FILE_IS_EXISTS = new ErrorCode(1020004001, "员工信息文件存在，不可重复创建");
    
    // === 员工工作经历 1020005000 ===
    ErrorCode EMPLOYEE_WORKHISTORY_NOT_EXISTS = new ErrorCode(1020005000, "员工工作经历不存在");
    ErrorCode EMPLOYEE_WORKHISTORY_NO_PERMISSION = new ErrorCode(1020005001, "没有修改权限");
    
    // === 员工基本信息 1020006000 ===
    ErrorCode EMPLOYEE_BASIC_NOT_EXISTS = new ErrorCode(1020006000, "员工基本信息不存在");
    ErrorCode EMPLOYEE_BASIC_IS_EXISTS = new ErrorCode(1020006001, "员工基本信息存在，不可重复创建");
    ErrorCode EMPLOYEE_BASIC_FINISHED_NO_PERMISSION = new ErrorCode(1020006002, "审核已通过，没有权限修改");

    // === 工资总 1020007000 ===
    ErrorCode SALARY_NOT_EXISTS = new ErrorCode(1020007000, "工资总不存在");
    
    // === 绩效工资 1020008000 ===
    ErrorCode SALARYCOMMISSION_NOT_EXISTS = new ErrorCode(1020008000, "绩效工资不存在");
    
    // === 工资明细 1020009000 ===
    ErrorCode SALARYDETAIL_NOT_EXISTS = new ErrorCode(1020009000, "工资明细不存在");
    
    // === 工资提成 1020010000 ===
    ErrorCode TRADECOMMISSION_NOT_EXISTS = new ErrorCode(1020010000, "工资提成不存在");
    

}
