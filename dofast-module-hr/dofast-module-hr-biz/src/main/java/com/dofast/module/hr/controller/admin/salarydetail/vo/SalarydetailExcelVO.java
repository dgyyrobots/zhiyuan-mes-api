package com.dofast.module.hr.controller.admin.salarydetail.vo;

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
 * 工资明细 Excel VO
 *
 * @author 惠智造
 */
@Data
public class SalarydetailExcelVO {

    @ExcelProperty("工资明细id")
    private Integer id;

    @ExcelProperty("工资")
    private Integer salary;

    @ExcelProperty(value = "科目", converter = DictConvert.class)
    @DictFormat("hr_salary_item") // TODO 代码优化：建议设置到对应的 XXXDictTypeConstants 枚举类中
    private String item;

    @ExcelProperty("类型")
    private String type;

    @ExcelProperty("金额")
    private BigDecimal amount;

    @ExcelProperty("描述")
    private String desc;

    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}
