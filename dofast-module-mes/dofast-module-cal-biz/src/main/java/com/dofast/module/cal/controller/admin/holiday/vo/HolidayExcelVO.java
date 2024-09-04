package com.dofast.module.cal.controller.admin.holiday.vo;

import java.time.LocalDate;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;

import com.alibaba.excel.annotation.ExcelProperty;
import com.dofast.framework.excel.core.annotations.DictFormat;
import com.dofast.framework.excel.core.convert.DictConvert;


/**
 * 节假日设置 Excel VO
 *
 * @author 惠智造
 */
@Data
public class HolidayExcelVO {

    @ExcelProperty("流水号id")
    private Long id;

    @ExcelProperty("日期")
    private LocalDateTime theDay;

    @ExcelProperty(value = "日期类型", converter = DictConvert.class)
    @DictFormat("jiaqi_type") // TODO 代码优化：建议设置到对应的 XXXDictTypeConstants 枚举类中
    private String holidayType;

    @ExcelProperty("开始时间")
    private LocalDateTime startTime;

    @ExcelProperty("结束时间")
    private LocalDateTime endTime;

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
