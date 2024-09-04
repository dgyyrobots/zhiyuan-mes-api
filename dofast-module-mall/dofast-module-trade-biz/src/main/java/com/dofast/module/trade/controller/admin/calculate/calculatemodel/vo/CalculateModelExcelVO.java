package com.dofast.module.trade.controller.admin.calculate.calculatemodel.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 计价模型 Excel VO
 *
 * @author 惠智造
 */
@Data
public class CalculateModelExcelVO {

    @ExcelProperty("ID")
    private Integer id;

    @ExcelProperty("名称")
    private String name;

    @ExcelProperty("备注")
    private String description;

    @ExcelProperty("计价类型id")
    private Integer categoryId;

    @ExcelProperty("表单")
    private Long formId;

    @ExcelProperty("表达式类型")
    private String type;

    @ExcelProperty("表达式")
    private String expression;

    @ExcelProperty("状态")
    private Integer status;

    @ExcelProperty("排序")
    private Integer sort;

    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}
