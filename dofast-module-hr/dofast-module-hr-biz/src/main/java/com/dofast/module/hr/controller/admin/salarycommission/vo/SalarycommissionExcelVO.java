package com.dofast.module.hr.controller.admin.salarycommission.vo;

import java.time.LocalDate;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.alibaba.excel.annotation.ExcelProperty;
import com.dofast.framework.excel.core.annotations.DictFormat;
import com.dofast.framework.excel.core.convert.DictConvert;


/**
 * 绩效工资 Excel VO
 *
 * @author 惠智造
 */
@Data
public class SalarycommissionExcelVO {

    @ExcelProperty("绩效工资id")
    private Integer id;

    @ExcelProperty("工资")
    private Integer salary;

    @ExcelProperty(value = "绩效类型", converter = DictConvert.class)
    @DictFormat("hr_salary_type") // TODO 代码优化：建议设置到对应的 XXXDictTypeConstants 枚举类中
    private String type;

    @ExcelProperty("绩效标准")
    private String line;

    @ExcelProperty("绩效金额")
    private BigDecimal amount;

    @ExcelProperty("比例")
    private BigDecimal rate;

    @ExcelProperty("绩效")
    private BigDecimal commission;

    @ExcelProperty("描述")
    private String desc;

    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}
