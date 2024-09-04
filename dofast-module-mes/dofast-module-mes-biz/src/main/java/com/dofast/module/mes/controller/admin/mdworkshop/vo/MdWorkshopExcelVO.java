package com.dofast.module.mes.controller.admin.mdworkshop.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * 车间 Excel VO
 *
 * @author 芋道源码
 */
@Data
public class MdWorkshopExcelVO {

    @ExcelProperty("车间ID")
    private Long id;

    @ExcelProperty("车间编码")
    private String workshopCode;

    @ExcelProperty("车间名称")
    private String workshopName;

    @ExcelProperty("面积")
    private Object area;

    @ExcelProperty("负责人")
    private String charge;

    @ExcelProperty("是否启用")
    private String enableFlag;

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
