package com.dofast.module.mes.api.ItemApi.dto;

import lombok.Data;

@Data
public class MdItemDTO {
    /**
     * 产品物料ID
     */
    private Long id;
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
     * 产品物料标识
     */
    private String itemOrProduct;
    /**
     * 物料类型ID
     */
    private Long itemTypeId;
    /**
     * 物料类型编码
     */
    private String itemTypeCode;
    /**
     * 物料类型名称
     */
    private String itemTypeName;
    /**
     * 是否启用
     */
    private String enableFlag;
    /**
     * 是否设置安全库存
     */
    private String safeStockFlag;
    /**
     * 最低库存量
     */
    private Double minStock;
    /**
     * 最大库存量
     */
    private Double maxStock;
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
}
