package com.dofast.module.pro.controller.admin.workorderbom.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * 生产工单BOM组成 Excel VO
 *
 * @author 芋道源码
 */
@Data
public class WorkorderBomExcelVO {

    @ExcelProperty("行ID")
    private Long id;

    @ExcelProperty("生产工单ID")
    private Long workorderId;

    @ExcelProperty("BOM物料ID")
    private Long itemId;

    @ExcelProperty("BOM物料编号")
    private String itemCode;

    @ExcelProperty("BOM物料名称")
    private String itemName;

    @ExcelProperty("规格型号")
    private String itemSpc;

    @ExcelProperty("单位")
    private String unitOfMeasure;

    @ExcelProperty("物料产品标识")
    private String itemOrProduct;

    @ExcelProperty("预计使用量")
    private Double quantity;

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

    @ExcelProperty("项次")
    private Long sequence;

}
