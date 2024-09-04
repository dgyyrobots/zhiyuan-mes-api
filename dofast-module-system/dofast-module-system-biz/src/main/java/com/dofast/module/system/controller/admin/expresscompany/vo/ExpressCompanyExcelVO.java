package com.dofast.module.system.controller.admin.expresscompany.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 租户物流公司 Excel VO
 *
 * @author 惠智造
 */
@Data
public class ExpressCompanyExcelVO {

    @ExcelProperty("ID")
    private Integer id;

    @ExcelProperty("物流公司id")
    private Integer companyId;

    @ExcelProperty("物流公司名称")
    private String companyName;

    @ExcelProperty("logo")
    private String logo;

    @ExcelProperty("打印内容")
    private String contentJson;

    @ExcelProperty("背景图")
    private String backgroundImage;

    @ExcelProperty("打印字体")
    private String fontSize;

    @ExcelProperty("宽度")
    private String width;

    @ExcelProperty("高度")
    private String height;

    @ExcelProperty("真实尺寸（mm）与显示尺寸（px）的比例")
    private BigDecimal scale;

    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

    @ExcelProperty("是否支持电子面单（0不支持  1支持）")
    private Byte isElectronicsheet;

    @ExcelProperty("编码（默认）")
    private String expressNo;

    @ExcelProperty("编码（点三）")
    private String expressNoDiansan;

    @ExcelProperty("编码（快递100）")
    private String expressNoKd100;

    @ExcelProperty("编码（菜鸟）")
    private String expressNoCainiao;

    @ExcelProperty("电子面单打印风格")
    private String printStyle;

}
