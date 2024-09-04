package com.dofast.module.member.controller.admin.loction.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * 三级位置信息 Excel VO
 *
 * @author 惠智造
 */
@Data
public class LocationExcelVO {

    @ExcelProperty("主键")
    private Integer id;

    @ExcelProperty("省市区名称")
    private String name;

    @ExcelProperty("上级ID")
    private Integer parentid;

    @ExcelProperty("简称")
    private String shortname;

    @ExcelProperty("级别:0,中国；1，省分；2，市；3，区、县")
    private Byte leveltype;

    @ExcelProperty("城市代码")
    private String citycode;

    @ExcelProperty("邮编")
    private String zipcode;

    @ExcelProperty("经度")
    private String lng;

    @ExcelProperty("纬度")
    private String lat;

    @ExcelProperty("拼音")
    private String pinyin;

    @ExcelProperty("枚举")
    private String status;

}
