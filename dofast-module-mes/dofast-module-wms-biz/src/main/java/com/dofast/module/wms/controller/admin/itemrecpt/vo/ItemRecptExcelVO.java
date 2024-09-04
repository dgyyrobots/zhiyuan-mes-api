package com.dofast.module.wms.controller.admin.itemrecpt.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * 物料入库单 Excel VO
 *
 * @author 芋道源码
 */
@Data
public class ItemRecptExcelVO {

    @ExcelProperty("入库单ID")
    private Long id;

    @ExcelProperty("入库单编号")
    private String recptCode;

    @ExcelProperty("入库单名称")
    private String recptName;

    @ExcelProperty("来料检验单ID")
    private Long iqcId;

    @ExcelProperty("来料检验单编号")
    private String iqcCode;

    @ExcelProperty("采购订单编号")
    private String poCode;

    @ExcelProperty("供应商ID")
    private Long vendorId;

    @ExcelProperty("供应商编码")
    private String vendorCode;

    @ExcelProperty("供应商名称")
    private String vendorName;

    @ExcelProperty("供应商简称")
    private String vendorNick;

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

    @ExcelProperty("单据状态")
    private String status;

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
