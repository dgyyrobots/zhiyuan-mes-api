package com.dofast.module.mes.controller.admin.Autocodepart.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * 编码生成规则组成 Excel VO
 *
 * @author 芋道源码
 */
@Data
public class AutoCodePartExcelVO {

    @ExcelProperty("分段ID")
    private Long id;

    @ExcelProperty("规则ID")
    private Long ruleId;

    @ExcelProperty("分段序号")
    private Integer partIndex;

    @ExcelProperty("分段类型，INPUTCHAR：输入字符，NOWDATE：当前日期时间，FIXCHAR：固定字符，SERIALNO：流水号")
    private String partType;

    @ExcelProperty("分段编号")
    private String partCode;

    @ExcelProperty("分段名称")
    private String partName;

    @ExcelProperty("分段长度")
    private Integer partLength;

    @ExcelProperty("格式化")
    private String dateFormat;

    @ExcelProperty("输入字符")
    private String inputCharacter;

    @ExcelProperty("固定字符")
    private String fixCharacter;

    @ExcelProperty("流水号起始值")
    private Integer seriaStartNo;

    @ExcelProperty("流水号步长")
    private Integer seriaStep;

    @ExcelProperty("流水号当前值")
    private Integer seriaNowNo;

    @ExcelProperty("流水号是否循环")
    private String cycleFlag;

    @ExcelProperty("循环方式，YEAR：按年，MONTH：按月，DAY：按天，HOUR：按小时，MINITE：按分钟，OTHER：按传入字符变")
    private String cycleMethod;

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
