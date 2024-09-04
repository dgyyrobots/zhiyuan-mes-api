package com.dofast.module.pro.controller.admin.processcontent.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * 生产工序内容 Excel VO
 *
 * @author 芋道源码
 */
@Data
public class ProcessContentExcelVO {

    @ExcelProperty("内容ID")
    private Long id;

    @ExcelProperty("工序ID")
    private Long processId;

    @ExcelProperty("顺序编号")
    private Integer orderNum;

    @ExcelProperty("内容说明")
    private String contentText;

    @ExcelProperty("辅助设备")
    private String device;

    @ExcelProperty("辅助材料")
    private String material;

    @ExcelProperty("材料URL")
    private String docUrl;

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

}
