package com.dofast.module.report.controller.admin.PrintLog.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 打印日志 Excel VO
 *
 * @author a1
 */
@Data
public class PrintLogExcelVO {

    @ExcelProperty("打印记录ID")
    private Long id;

    @ExcelProperty("打印编码")
    private String printCode;

    @ExcelProperty("打印人")
    private String printName;

    @ExcelProperty("打印类型")
    private String printType;

    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}
