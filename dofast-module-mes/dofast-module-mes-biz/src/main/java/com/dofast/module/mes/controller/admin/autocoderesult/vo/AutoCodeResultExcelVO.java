package com.dofast.module.mes.controller.admin.autocoderesult.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * 编码生成记录 Excel VO
 *
 * @author 芋道源码
 */
@Data
public class AutoCodeResultExcelVO {

    @ExcelProperty("记录ID")
    private Long id;

    @ExcelProperty("规则ID")
    private Long ruleId;

    @ExcelProperty("生成日期时间")
    private String genDate;

    @ExcelProperty("最后产生的序号")
    private Integer genIndex;

    @ExcelProperty("最后产生的值")
    private String lastResult;

    @ExcelProperty("最后产生的流水号")
    private Integer lastSerialNo;

    @ExcelProperty("最后传入的参数")
    private String lastInputChar;

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

}
