package com.dofast.module.hr.controller.admin.employeefamiles.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * 员工家庭成员 Excel VO
 *
 * @author 惠智造
 */
@Data
public class EmployeeFamilesExcelVO {

    @ExcelProperty("家庭成员姓名")
    private String familesName;

    @ExcelProperty("家庭成员关系")
    private String familesRealtion;

    @ExcelProperty("家庭成员工作单位")
    private String familesWorkunit;

    @ExcelProperty("家庭成员工作地区")
    private String familesWorkplace;

    @ExcelProperty("家庭成员电话")
    private Integer familesPhone;

    @ExcelProperty("家庭成员id")
    private Long id;

    @ExcelProperty("员工id")
    private Long employeeId;

    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}
