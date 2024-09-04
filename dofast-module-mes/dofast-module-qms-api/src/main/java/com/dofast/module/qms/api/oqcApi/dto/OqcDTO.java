package com.dofast.module.qms.api.oqcApi.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 出货检验单 DO
 *
 * @author 芋道源码
 */
@Data
@ToString(callSuper = true)
public class OqcDTO{

    /**
     * 出货检验单ID
     */
    private Long id;
    /**
     * 出货检验单编号
     */
    private String oqcCode;
    /**
     * 出货检验单名称
     */
    private String oqcName;
    /**
     * 检验模板ID
     */
    private Long templateId;
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
     * 最低检测数
     */
    private BigDecimal quantityMinCheck;
    /**
     * 最大不合格数
     */
    private BigDecimal quantityMaxUnqualified;
    /**
     * 发货数量
     */
    private BigDecimal quantityOut;
    /**
     * 本次检测数量
     */
    private BigDecimal quantityCheck;
    /**
     * 不合格数
     */
    private BigDecimal quantityUnqualified;
    /**
     * 合格数量
     */
    private BigDecimal quantityQuanlified;
    /**
     * 致命缺陷率
     */
    private BigDecimal crRate;
    /**
     * 严重缺陷率
     */
    private BigDecimal majRate;
    /**
     * 轻微缺陷率
     */
    private BigDecimal minRate;
    /**
     * 致命缺陷数量
     */
    private BigDecimal crQuantity;
    /**
     * 严重缺陷数量
     */
    private BigDecimal majQuantity;
    /**
     * 轻微缺陷数量
     */
    private BigDecimal minQuantity;
    /**
     * 检测结果
     */
    private String checkResult;
    /**
     * 出货日期
     */
    private LocalDateTime outDate;
    /**
     * 检测日期
     */
    private LocalDateTime inspectDate;
    /**
     * 检测人员
     */
    private String inspector;
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

}
