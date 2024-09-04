package com.dofast.module.hr.controller.admin.employeeeducation.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * 员工教育培训经历 Excel VO
 *
 * @author 惠智造
 */
@Data
public class EmployeeEducationExcelVO {

    @ExcelProperty("学校")
    private String educationSchool;

    @ExcelProperty("专业")
    private String educationSpecialty;

    @ExcelProperty("所获荣誉")
    private String educationHonor;

    @ExcelProperty("教育开始时间")
    private LocalDateTime educationStarttime;

    @ExcelProperty("教育结束时间")
    private LocalDateTime educationEndtime;

    @ExcelProperty("培训教育经历id")
    private Long id;

    @ExcelProperty("员工id")
    private Long employeeId;

    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}
