package com.dofast.module.purchase.dal.dataobject.invoice;

import java.time.LocalDate;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import com.dofast.framework.mybatis.core.dataobject.BaseDO;

/**
 * 采购入库单 DO
 *
 * @author 惠智造
 */
@TableName("purchase_invoice")
@KeySequence("purchase_invoice_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceDO extends BaseDO {

    /**
     * id
     */
    @TableId
    private Integer id;

    /**
     * 流程实例的编号
     */
    private String processInstanceId;

    /**
     * 采购单号
     */
    private String purchaseOrderNum;
    /**
     * 入库单号
     */
    private String shipmentNum;
    /**
     * 订单
     */
    private Integer purchaseId;
    /**
     * 供应商
     */
    private Integer supplierId;
    /**
     * 付款类型(0应付账款1现金付款2预付款)
     *
     * 枚举 {@link TODO purchase_invoice_pay_way 对应的类}
     */
    private Integer paymentType;
    /**
     * 有无退货
     *
     * 枚举 {@link TODO infra_boolean_string 对应的类}
     */
    private Integer isReturn;
    /**
     * 供应商联系人
     */
    private String supplieruser;
    /**
     * 供应商手机号
     */
    private String supplierphone;
    /**
     * 采购总价
     */
    private Object totalPrice;
    /**
     * 是否入库
     *
     * 枚举 {@link TODO infra_boolean_string 对应的类}
     */
    private Integer isWarehousing;

}
