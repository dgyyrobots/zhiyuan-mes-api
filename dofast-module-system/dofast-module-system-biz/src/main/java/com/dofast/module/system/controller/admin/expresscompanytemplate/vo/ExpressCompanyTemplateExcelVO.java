package com.dofast.module.system.controller.admin.expresscompanytemplate.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 系统物流公司 Excel VO
 *
 * @author 惠智造
 */
@Data
public class ExpressCompanyTemplateExcelVO {

    @ExcelProperty("公司ID")
    private Integer companyId;

    @ExcelProperty("站点id")
    private Integer siteId;

    @ExcelProperty("物流公司名称")
    private String companyName;

    @ExcelProperty("物流公司logo")
    private String logo;

    @ExcelProperty("物流公司网址")
    private String url;

    @ExcelProperty("排序")
    private Integer sort;

    @ExcelProperty("编码")
    private String expressNo;

    @ExcelProperty("编码（快递100）")
    private String expressNoKd100;

    @ExcelProperty("编码（菜鸟）")
    private String expressNoCainiao;

    @ExcelProperty("打印内容")
    private String contentJson;

    @ExcelProperty("背景图")
    private String backgroundImage;

    @ExcelProperty("打印字体")
    private Integer fontSize;

    @ExcelProperty("宽度")
    private Integer width;

    @ExcelProperty("高度")
    private Integer height;

    @ExcelProperty("真实尺寸（mm）与显示尺寸（px）的比例")
    private BigDecimal scale;

    @ExcelProperty("是否支持电子面单（0不支持  1支持）")
    private Byte isElectronicsheet;

    @ExcelProperty("电子面单打印风格")
    private String printStyle;

    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}
