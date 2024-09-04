package com.dofast.module.mes.controller.admin.Autocoderule.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * 编码生成规则 Excel VO
 *
 * @author 芋道源码
 */
@Data
public class AutoCodeRuleExcelVO {

    @ExcelProperty("规则ID")
    private Long id;

    @ExcelProperty("规则编码")
    private String ruleCode;

    @ExcelProperty("规则名称")
    private String ruleName;

    @ExcelProperty("描述")
    private String ruleDesc;

    @ExcelProperty("最大长度")
    private Integer maxLength;

    @ExcelProperty("是否补齐")
    private String isPadded;

    @ExcelProperty("补齐字符")
    private String paddedChar;

    @ExcelProperty("补齐方式")
    private String paddedMethod;

    @ExcelProperty("是否启用")
    private String enableFlag;

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
