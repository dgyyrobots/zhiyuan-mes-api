package com.dofast.module.pro.api.WorkorderApi.dto;

import lombok.Data;

@Data
public class WorkorderBomDTO {

    /**
     * 行ID
     */
    private Long id;
    /**
     * 生产工单ID
     */
    private Long workorderId;
    /**
     * BOM物料ID
     */
    private Long itemId;
    /**
     * BOM物料编号
     */
    private String itemCode;
    /**
     * BOM物料名称
     */
    private String itemName;
    /**
     * 规格型号
     */
    private String itemSpc;
    /**
     * 单位
     */
    private String unitOfMeasure;
    /**
     * 物料产品标识
     */
    private String itemOrProduct;
    /**
     * 预计使用量
     */
    private Double quantity;
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
     * 项次
     */
    private Long sequence;

    /**
     * 项序
     */
    private Long sequenceOrder;

}
