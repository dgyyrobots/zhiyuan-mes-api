package com.dofast.module.qms.controller.admin.index.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * 检测项 Excel VO
 *
 * @author 芋道源码
 */
@Data
public class IndexExcelVO {

    @ExcelProperty("检测项ID")
    private Long id;

    @ExcelProperty("检测项编码")
    private String indexCode;

    @ExcelProperty("检测项名称")
    private String indexName;

    @ExcelProperty("检测项类型")
    private String indexType;

    @ExcelProperty("检测工具")
    private String qcTool;

    @ExcelProperty("备注")
    private String remark;

    @ExcelProperty("预留字段1")
    private String attr1;

    @ExcelProperty("预留字段2")
    private String attr2;

    @ExcelProperty("预留字段3")
    private Integer attr3;

    @ExcelProperty("预留字段4")
    private Integer attr4;

    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

    @ExcelProperty("工序编码")
    private String processCode;

}
