package com.dofast.module.pro.controller.admin.processdefect.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * 工序异常缺陷名称 Excel VO
 *
 * @author 惠智造
 */
@Data
public class ProcessDefectExcelVO {

    @ExcelProperty("主键id")
    private Long id;

    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

    @ExcelProperty("工序编码")
    private String processCode;

    @ExcelProperty("缺陷名称")
    private String defectName;

}
