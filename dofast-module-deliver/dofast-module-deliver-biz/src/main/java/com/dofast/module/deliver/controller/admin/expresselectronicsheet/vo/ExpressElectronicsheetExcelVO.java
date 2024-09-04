package com.dofast.module.deliver.controller.admin.expresselectronicsheet.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * 电子面单 Excel VO
 *
 * @author a1
 */
@Data
public class ExpressElectronicsheetExcelVO {

    @ExcelProperty("ID")
    private Long id;

    @ExcelProperty("配送快递名字")
    private String companyName;

    @ExcelProperty("类型")
    private String type;

    @ExcelProperty("配送信息")
    private String info;

    @ExcelProperty("配送编码(快递鸟)")
    private String kdnCode;

    @ExcelProperty("信息模板")
    private String template;

    @ExcelProperty("快递配置信息")
    private String config;

    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}
