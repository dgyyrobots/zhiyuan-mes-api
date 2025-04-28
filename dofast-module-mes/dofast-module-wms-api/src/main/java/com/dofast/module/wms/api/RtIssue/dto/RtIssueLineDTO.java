package com.dofast.module.wms.api.RtIssue.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class RtIssueLineDTO {

    private Long id;

    private Long rtId;

    private Long materialStockId;

    private Long itemId;

    private String itemCode;

    private String itemName;

    private String specification;

    private String unitOfMeasure;

    private BigDecimal quantityRt;

    private String batchCode;

    private Long warehouseId;

    private String warehouseCode;

    private String warehouseName;

    private Long locationId;

    private String locationCode;

    private String locationName;

    private Long areaId;

    private String areaCode;

    private String areaName;

    private String remark;

    private String attr1;

    private String attr2;

    private Integer attr3;

    private Integer attr4;

    private String vendorCode;

    private Long sequence;

    private Long sequenceOrder;

    private String erpBatchCode;


}
