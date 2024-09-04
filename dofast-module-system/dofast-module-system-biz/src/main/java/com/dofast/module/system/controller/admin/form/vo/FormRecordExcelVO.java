package com.dofast.module.system.controller.admin.form.vo;

import java.time.LocalDate;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * 表单历史 Excel VO
 *
 * @author 惠智造
 */
@Data
public class FormRecordExcelVO {

    @ExcelProperty("ID")
    private Long id;

    @ExcelProperty("计价规则")
    private Long formId;

    @ExcelProperty("表单内容")
    private String value;

    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}
