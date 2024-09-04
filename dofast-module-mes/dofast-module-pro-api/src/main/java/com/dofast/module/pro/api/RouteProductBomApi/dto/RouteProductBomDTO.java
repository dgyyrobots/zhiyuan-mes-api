package com.dofast.module.pro.api.RouteProductBomApi.dto;

import lombok.Data;

@Data
public class RouteProductBomDTO {
    /**
     * 记录ID
     */
    private Long id;
    /**
     * 工艺路线ID
     */
    private Long routeId;
    /**
     * 工序ID
     */
    private Long processId;
    /**
     * 产品BOM中的唯一ID
     */
    private Long productId;
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
     * 用料比例
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
}
