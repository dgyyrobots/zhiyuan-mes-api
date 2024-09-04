package com.dofast.module.mes.controller.admin.mdworkstationtool.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * 工装夹具资源 Excel VO
 *
 * @author 芋道源码
 */
@Data
public class MdWorkstationToolExcelVO {

    @ExcelProperty("记录ID")
    private Long id;

    @ExcelProperty("工作站ID")
    private Long workstationId;

    @ExcelProperty("工装夹具类型ID")
    private Long toolTypeId;

    @ExcelProperty("类型编码")
    private String toolTypeCode;

    @ExcelProperty("类型名称")
    private String toolTypeName;

    @ExcelProperty("数量")
    private Integer quantity;

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
