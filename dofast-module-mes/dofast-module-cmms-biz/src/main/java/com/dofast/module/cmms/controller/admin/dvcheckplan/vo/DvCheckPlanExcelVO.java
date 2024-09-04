package com.dofast.module.cmms.controller.admin.dvcheckplan.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * 设备点检保养计划头 Excel VO
 *
 * @author 芋道源码
 */
@Data
public class DvCheckPlanExcelVO {

    @ExcelProperty("计划ID")
    private Long id;

    @ExcelProperty("计划编码")
    private String planCode;

    @ExcelProperty("计划名称")
    private String planName;

    @ExcelProperty("计划类型")
    private String planType;

    @ExcelProperty("开始日期")
    private LocalDateTime startDate;

    @ExcelProperty("结束日期")
    private LocalDateTime endDate;

    @ExcelProperty("频率")
    private String cycleType;

    @ExcelProperty("次数")
    private Integer cycleCount;

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
