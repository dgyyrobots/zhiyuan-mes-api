package com.dofast.module.trade.controller.admin.calculate.calculatecategory.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 计价分类 Excel VO
 *
 * @author 惠智造
 */
@Data
public class CalculateCategoryExcelVO {

    @ExcelProperty("ID")
    private Integer id;

    @ExcelProperty("分类名称")
    private String name;

    @ExcelProperty("备注")
    private String description;

    @ExcelProperty("状态")
    private Integer status;

    @ExcelProperty("排序")
    private Integer sort;

    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}
