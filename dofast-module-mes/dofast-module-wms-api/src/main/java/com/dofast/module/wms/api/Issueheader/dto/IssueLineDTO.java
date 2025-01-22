package com.dofast.module.wms.api.Issueheader.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class IssueLineDTO {

    /**
     * 行ID
     */
    private Long id;
    /**
     * 领料单ID
     */
    private Long issueId;
    /**
     * 库存ID
     */
    private Long materialStockId;
    /**
     * 产品物料ID
     */
    private Long itemId;
    /**
     * 产品物料编码
     */
    private String itemCode;
    /**
     * 产品物料名称
     */
    private String itemName;
    /**
     * 规格型号
     */
    private String specification;
    /**
     * 单位
     */
    private String unitOfMeasure;
    /**
     * 领料数量
     */
    private BigDecimal quantityIssued;
    /**
     * 领料批次号
     */
    private String batchCode;
    /**
     * 仓库ID
     */
    private Long warehouseId;
    /**
     * 仓库编码
     */
    private String warehouseCode;
    /**
     * 仓库名称
     */
    private String warehouseName;
    /**
     * 库区ID
     */
    private Long locationId;
    /**
     * 库区编码
     */
    private String locationCode;
    /**
     * 库区名称
     */
    private String locationName;
    /**
     * 库位ID
     */
    private Long areaId;
    /**
     * 库位编码
     */
    private String areaCode;
    /**
     * 库位名称
     */
    private String areaName;
    /**
     * 备注
     */
    private String remark;
    /**
     * 预留字段1
     */
    private String attr1;
    /**
     * 预留字段2
     */
    private String attr2;
    /**
     * 预留字段3
     */
    private Integer attr3;
    /**
     * 预留字段4
     */
    private Integer attr4;

    /**
     * 单身领料状态
     */
    private String status;

    /**
     * 供应商编码
     */
    private String vendorCode;

    /**
     * 报工状态
     */
    private String feedbackStatus;

    /**
     * 报工单号
     */
    private String feedbackCode;



}
