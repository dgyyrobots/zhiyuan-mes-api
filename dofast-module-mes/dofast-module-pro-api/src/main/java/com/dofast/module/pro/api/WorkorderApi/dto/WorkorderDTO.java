package com.dofast.module.pro.api.WorkorderApi.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class  WorkorderDTO {
    /**
     * 工单ID
     */
    private Long id;
    /**
     * 工单编码
     */
    private String workorderCode;
    /**
     * 工单名称
     */
    private String workorderName;
    /**
     * 来源类型
     */
    private String orderSource;
    /**
     * 来源单据
     */
    private String sourceCode;
    /**
     * 产品ID
     */
    private Long productId;
    /**
     * 产品编号
     */
    private String productCode;
    /**
     * 产品名称
     */
    private String productName;
    /**
     * 规格型号
     */
    private String productSpc;
    /**
     * 单位
     */
    private String unitOfMeasure;
    /**
     * 生产数量
     */
    private BigDecimal quantity;
    /**
     * 已生产数量
     */
    private BigDecimal quantityProduced;
    /**
     * 调整数量
     */
    private BigDecimal quantityChanged;
    /**
     * 已排产数量
     */
    private BigDecimal quantityScheduled;
    /**
     * 客户ID
     */
    private Long clientId;
    /**
     * 客户编码
     */
    private String clientCode;
    /**
     * 客户名称
     */
    private String clientName;
    /**
     * 批次号
     */
    private String batchCode;
    /**
     * 需求日期
     */
    private LocalDateTime requestDate;
    /**
     * 父工单
     */
    private Long parentId;
    /**
     * 所有父节点ID
     */
    private String ancestors;
    /**
     * 单据状态
     */
    private String status;
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
     * 销售单ID
     */
    private Long mixinOrderId;
    /**
     * 附件列表
     */
    private String adjuncts;
    /**
     * 是否外协
     */
    private Boolean isOut;
    /**
     * 工艺编码
     */
    private String routeCode;

}
