package com.dofast.module.mes.controller.admin.mditem.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * 物料产品 Excel VO
 *
 * @author 芋道源码
 */
@Data
public class MdItemExcelVO {

    @ExcelProperty("产品物料ID")
    private Long id;

    @ExcelProperty("产品物料编码")
    private String itemCode;

    @ExcelProperty("产品物料名称")
    private String itemName;

    @ExcelProperty("规格型号")
    private String specification;

    @ExcelProperty("单位")
    private String unitOfMeasure;

    @ExcelProperty("产品物料标识")
    private String itemOrProduct;

    @ExcelProperty("物料类型ID")
    private Long itemTypeId;

    @ExcelProperty("物料类型编码")
    private String itemTypeCode;

    @ExcelProperty("物料类型名称")
    private String itemTypeName;

    @ExcelProperty("是否启用")
    private String enableFlag;

    @ExcelProperty("是否设置安全库存")
    private String safeStockFlag;

    @ExcelProperty("最低库存量")
    private BigDecimal minStock;

    @ExcelProperty("最大库存量")
    private BigDecimal maxStock;

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

    @ExcelProperty("仓库ID")
    private Long warehouseId;

    @ExcelProperty("仓库编码")
    private String warehouseCode;

    @ExcelProperty("仓库名称")
    private String warehouseName;

    @ExcelProperty("库区ID")
    private Long locationId;

    @ExcelProperty("库区编码")
    private String locationCode;

    @ExcelProperty("库区名称")
    private String locationName;

    @ExcelProperty("库位ID")
    private Long areaId;

    @ExcelProperty("库位编码")
    private String areaCode;

    @ExcelProperty("库位名称")
    private String areaName;

    @ExcelProperty("入库日期")
    private LocalDateTime recptDate;

}
