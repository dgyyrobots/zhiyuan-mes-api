package com.dofast.module.pro.controller.admin.routeproduct.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * 产品制程 Excel VO
 *
 * @author 芋道源码
 */
@Data
public class RouteProductExcelVO {

    @ExcelProperty("记录ID")
    private Long id;

    @ExcelProperty("工艺路线ID")
    private Long routeId;

    @ExcelProperty("产品物料ID")
    private Long itemId;

    @ExcelProperty("产品物料编码")
    private String itemCode;

    @ExcelProperty("产品物料名称")
    private String itemName;

    @ExcelProperty("规格型号")
    private String specification;

    @ExcelProperty("单位")
    private String unitOfMeasure;

    @ExcelProperty("生产数量")
    private Integer quantity;

    @ExcelProperty("生产用时")
    private BigDecimal productionTime;

    @ExcelProperty("时间单位")
    private String timeUnitType;

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
