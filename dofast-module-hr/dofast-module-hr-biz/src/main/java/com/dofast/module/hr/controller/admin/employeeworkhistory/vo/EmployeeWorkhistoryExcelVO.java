package com.dofast.module.hr.controller.admin.employeeworkhistory.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * 员工工作经历 Excel VO
 *
 * @author 惠智造
 */
@Data
public class EmployeeWorkhistoryExcelVO {

    @ExcelProperty("从业公司")
    private String companyName;

    @ExcelProperty("从业薪资")
    private Integer treatment;

    @ExcelProperty("担任职位")
    private String treatmentPost;

    @ExcelProperty("离职原因")
    private String reasonForLeave;

    @ExcelProperty("id")
    private Long id;

    @ExcelProperty("员工id")
    private Long employeeId;

    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}
