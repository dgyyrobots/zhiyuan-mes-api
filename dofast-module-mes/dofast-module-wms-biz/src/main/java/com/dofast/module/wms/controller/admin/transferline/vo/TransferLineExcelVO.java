package com.dofast.module.wms.controller.admin.transferline.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * 转移单行 Excel VO
 *
 * @author 芋道源码
 */
@Data
public class TransferLineExcelVO {

    @ExcelProperty("明细行ID")
    private Long id;

    @ExcelProperty("装箱单ID")
    private Long transferId;

    @ExcelProperty("库存记录ID")
    private Long materialStockId;

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

    @ExcelProperty("装箱数量")
    private Double quantityTransfer;

    @ExcelProperty("生产工单ID")
    private Long workorderId;

    @ExcelProperty("生产工单编号")
    private String workorderCode;

    @ExcelProperty("批次号")
    private String batchCode;

    @ExcelProperty("移出仓库ID")
    private Long fromWarehouseId;

    @ExcelProperty("移出仓库编码")
    private String fromWarehouseCode;

    @ExcelProperty("移出仓库名称")
    private String fromWarehouseName;

    @ExcelProperty("移出库区ID")
    private Long fromLocationId;

    @ExcelProperty("移出库区编码")
    private String fromLocationCode;

    @ExcelProperty("移出库区名称")
    private String fromLocationName;

    @ExcelProperty("移出库位ID")
    private Long fromAreaId;

    @ExcelProperty("移出库位编码")
    private String fromAreaCode;

    @ExcelProperty("移出库位名称")
    private String fromAreaName;

    @ExcelProperty("移入仓库ID")
    private Long toWarehouseId;

    @ExcelProperty("移入仓库编码")
    private String toWarehouseCode;

    @ExcelProperty("移入仓库名称")
    private String toWarehouseName;

    @ExcelProperty("移入库区ID")
    private Long toLocationId;

    @ExcelProperty("移入库区编码")
    private String toLocationCode;

    @ExcelProperty("移入库区名称")
    private String toLocationName;

    @ExcelProperty("移入库位ID")
    private Long toAreaId;

    @ExcelProperty("移入库位编码")
    private String toAreaCode;

    @ExcelProperty("移入库位名称")
    private String toAreaName;

    @ExcelProperty("有效期")
    private LocalDateTime expireDate;

    @ExcelProperty("供应商ID")
    private Long vendorId;

    @ExcelProperty("供应商编码")
    private String vendorCode;

    @ExcelProperty("供应商名称")
    private String vendorName;

    @ExcelProperty("供应商简称")
    private String vendorNick;

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
