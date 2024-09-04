package com.dofast.module.cal.controller.admin.planteam.vo;

import java.time.LocalDate;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * 计划班组 Excel VO
 *
 * @author 惠智造
 */
@Data
public class PlanTeamExcelVO {

    @ExcelProperty("流水号id")
    private Long id;

    @ExcelProperty("计划ID")
    private Long planId;

    @ExcelProperty("班组ID")
    private Long teamId;

    @ExcelProperty("班组编号")
    private String teamCode;

    @ExcelProperty("班组名称")
    private String teamName;

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
