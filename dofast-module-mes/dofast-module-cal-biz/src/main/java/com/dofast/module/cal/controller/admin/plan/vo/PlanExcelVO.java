package com.dofast.module.cal.controller.admin.plan.vo;

import java.time.LocalDate;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;

import com.alibaba.excel.annotation.ExcelProperty;
import com.dofast.framework.excel.core.annotations.DictFormat;
import com.dofast.framework.excel.core.convert.DictConvert;


/**
 * 排班计划 Excel VO
 *
 * @author 惠智造
 */
@Data
public class PlanExcelVO {

    @ExcelProperty("计划ID")
    private Long id;

    @ExcelProperty("计划编号")
    private String planCode;

    @ExcelProperty("计划名称")
    private String planName;

    @ExcelProperty("班组类型")
    private String calendarType;

    @ExcelProperty("开始日期")
    private LocalDateTime startDate;

    @ExcelProperty("结束日期")
    private LocalDateTime endDate;

    @ExcelProperty(value = "轮班方式", converter = DictConvert.class)
    @DictFormat("mes_shift_method") // TODO 代码优化：建议设置到对应的 XXXDictTypeConstants 枚举类中
    private String shiftType;

    @ExcelProperty(value = "倒班方式", converter = DictConvert.class)
    @DictFormat("mes_shift_type") // TODO 代码优化：建议设置到对应的 XXXDictTypeConstants 枚举类中
    private String shiftMethod;

    @ExcelProperty("数")
    private Integer shiftCount;

    @ExcelProperty("状态")
    private String status;

    @ExcelProperty("备注")
    private String remark;

    @ExcelProperty("预留字段1")
    private String attr1;

    @ExcelProperty("预留字段2")
    private String attr2;

    @ExcelProperty("预留字段3")
    private Integer attr3;

    @ExcelProperty("预留字段4")
    private Integer attr4;

    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}
