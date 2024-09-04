package com.dofast.module.wms.controller.admin.storagelocation.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.math.BigDecimal;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * 库区 Excel VO
 *
 * @author 芋道源码
 */
@Data
public class StorageLocationExcelVO {

    @ExcelProperty("库区ID")
    private Long id;

    @ExcelProperty("库区编码")
    private String locationCode;

    @ExcelProperty("库区名称")
    private String locationName;

    @ExcelProperty("仓库ID")
    private Long warehouseId;

    @ExcelProperty("面积")
    private BigDecimal area;

    @ExcelProperty("是否开启库位管理")
    private String areaFlag;

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
