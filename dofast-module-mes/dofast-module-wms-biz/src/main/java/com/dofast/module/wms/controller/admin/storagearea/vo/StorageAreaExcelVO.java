package com.dofast.module.wms.controller.admin.storagearea.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.math.BigDecimal;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * 库位 Excel VO
 *
 * @author 芋道源码
 */
@Data
public class StorageAreaExcelVO {

    @ExcelProperty("库位ID")
    private Long id;

    @ExcelProperty("库位编码")
    private String areaCode;

    @ExcelProperty("库位名称")
    private String areaName;

    @ExcelProperty("库区ID")
    private Long locationId;

    @ExcelProperty("面积")
    private BigDecimal area;

    @ExcelProperty("最大载重量")
    private BigDecimal maxLoa;

    @ExcelProperty("库位位置X")
    private Integer positionX;

    @ExcelProperty("库位位置y")
    private Integer positionY;

    @ExcelProperty("库位位置z")
    private Integer positionZ;

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
