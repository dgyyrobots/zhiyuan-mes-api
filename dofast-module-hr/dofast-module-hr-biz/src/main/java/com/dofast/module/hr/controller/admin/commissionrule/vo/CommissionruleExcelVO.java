package com.dofast.module.hr.controller.admin.commissionrule.vo;

import java.time.LocalDate;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * 提成规则 Excel VO
 *
 * @author 惠智造
 */
@Data
public class CommissionruleExcelVO {

    @ExcelProperty("提成规则id")
    private Integer id;

    @ExcelProperty("月份")
    private String month;

    @ExcelProperty("账户")
    private String account;

    @ExcelProperty("薪资")
    private String sale;

    @ExcelProperty("线")
    private String line;

    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}
