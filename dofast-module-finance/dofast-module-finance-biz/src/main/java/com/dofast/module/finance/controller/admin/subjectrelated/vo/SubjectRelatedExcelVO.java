package com.dofast.module.finance.controller.admin.subjectrelated.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * 收支科目 Excel VO
 *
 * @author a1
 */
@Data
public class SubjectRelatedExcelVO {

    @ExcelProperty("收支科目id")
    private Integer id;

    @ExcelProperty("科目名称")
    private String subjectName;

    @ExcelProperty("科目类型(in收 out支)（0/1）")
    private Integer subjectType;

    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}
