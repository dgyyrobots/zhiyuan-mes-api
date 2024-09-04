package com.dofast.module.system.controller.admin.expresstemplateitem.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.dofast.framework.excel.core.annotations.DictFormat;
import com.dofast.framework.excel.core.convert.DictConvert;
import lombok.Data;

import java.time.LocalDateTime;


/**
 * 运费模板细节 Excel VO
 *
 * @author 惠智造
 */
@Data
public class ExpressTemplateItemExcelVO {

    @ExcelProperty("运费模板细节编号")
    private Long id;

    @ExcelProperty("模板id")
    private Long templateId;

    @ExcelProperty("可配送地址id序列")
    private String areaIds;

    @ExcelProperty("起步计算标准（首重，首件）")
    private Integer startUnit;

    @ExcelProperty("起步计算价格，单位（分）")
    private Integer startUnitPrice;

    @ExcelProperty("续步计算标准（每件，每kg）")
    private Integer plusPerUnit;

    @ExcelProperty("续步计算价格，单位（分）")
    private Integer plusPerUnitPrice;

    @ExcelProperty(value = "运费计算方式", converter = DictConvert.class)
    @DictFormat("express_cal_type") // TODO 代码优化：建议设置到对应的 XXXDictTypeConstants 枚举类中
    private Byte priceType;

    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}
