package com.dofast.module.purchase.dal.dataobject.order;

import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import com.dofast.framework.mybatis.core.dataobject.BaseDO;

/**
 * 采购订单 DO
 *
 * @author 惠智造
 */
@TableName("purchase_order")
@KeySequence("purchase_order_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderDO extends BaseDO {

    /**
     * 供应商
     */
    private Integer supplierId;

    /**
     * 流程实例的编号
     */
    private String processInstanceId;

    /**
     * 供应商联系人
     */
    private String supplierContact;
    /**
     * 供应商联系人电话
     */
    private String supplierPhone;
    /**
     * 采购金额
     */
    private BigDecimal purchaseAmount;
    /**
     * ID
     */
    @TableId
    private Integer id;
    /**
     * 采购单号
     */
    private String poNo;
    /**
     * 付款类型
     *
     * 枚举 {@link TODO purchase_invoice_pay_way 对应的类}
     */
    private Integer paymentType;
    /**
     * 入库类型(停用)
     */
    private Integer warehousingType;
    /**
     * 退货状态
     *
     * 枚举 {@link TODO infra_boolean_string 对应的类}
     */
    private Integer returnGoods;
    /**
     * 审核类型
     *
     * 枚举 {@link TODO purchase_order_audit_status 对应的类}
     */
    private Integer processType;
    /**
     * 备注
     */
    private String remarks;
    /**
     * 母批次号
     */
    private String parentBatchCode;

    private String serial;

    private String supplierCode;

    /**
     * ERP入库单号
     */
    private String warehousingCode;


}
