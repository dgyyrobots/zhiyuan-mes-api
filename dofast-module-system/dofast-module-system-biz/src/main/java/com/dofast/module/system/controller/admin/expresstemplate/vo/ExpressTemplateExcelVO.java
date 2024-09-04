package com.dofast.module.system.controller.admin.expresstemplate.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.dofast.framework.excel.core.annotations.DictFormat;
import com.dofast.framework.excel.core.convert.DictConvert;
import lombok.Data;

import java.time.LocalDateTime;


/**
 * 运费模板 Excel VO
 *
 * @author 惠智造
 */
@Data
public class ExpressTemplateExcelVO {

    @ExcelProperty("运费模板编号")
    private Long id;

    @ExcelProperty("模板名称")
    private String name;

    @ExcelProperty(value = "运费计算方式1.按件2重量", converter = DictConvert.class)
    @DictFormat("express_cal_type") // TODO 代码优化：建议设置到对应的 XXXDictTypeConstants 枚举类中
    private Byte priceType;

    @ExcelProperty("是否为默认模板")
    private Boolean defaulted;

    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}
