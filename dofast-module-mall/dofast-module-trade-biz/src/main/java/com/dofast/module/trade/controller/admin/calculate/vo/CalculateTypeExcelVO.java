package com.dofast.module.trade.controller.admin.calculate.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.dofast.framework.excel.core.annotations.DictFormat;
import com.dofast.framework.excel.core.convert.DictConvert;
import lombok.Data;

import java.time.LocalDateTime;


/**
 * 计价类型 Excel VO
 *
 * @author 惠智造
 */
@Data
public class CalculateTypeExcelVO {

    @ExcelProperty("ID")
    private Integer id;

    @ExcelProperty("名称")
    private String name;

    @ExcelProperty("表单")
    private String formId;

    @ExcelProperty(value = "表达式类型", converter = DictConvert.class)
    @DictFormat("trade_calculate_type") // TODO 代码优化：建议设置到对应的 XXXDictTypeConstants 枚举类中
    private String type;

    @ExcelProperty("表达式")
    private String expression;

    @ExcelProperty(value = "状态", converter = DictConvert.class)
    @DictFormat("trade_order_collection_status") // TODO 代码优化：建议设置到对应的 XXXDictTypeConstants 枚举类中
    private Integer status;

    @ExcelProperty("排序")
    private Integer sort;

    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}
