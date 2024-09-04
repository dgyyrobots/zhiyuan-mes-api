package com.dofast.module.purchase.dal.dataobject.refund;

import java.time.LocalDate;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import com.dofast.framework.mybatis.core.dataobject.BaseDO;

/**
 * 采购退货 DO
 *
 * @author 惠智造
 */
@TableName("purchase_return")
@KeySequence("purchase_return_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RefundDO extends BaseDO {

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
     * 订单
     */
    private Integer purchaseId;
    /**
     * 订单单号
     */
    private String purchaseOrderNum;
    /**
     * 入库单号
     */
    private String shipmentNum;
    /**
     * 退货总额
     */
    private BigDecimal returnBonus;
    /**
     * 是否退货
     *
     * 枚举 {@link TODO infra_boolean_string 对应的类}
     */
    private Integer isReturn;
    /**
     * 供应商
     */
    private Integer supplierId;
    /**
     * 付款类型
     *
     * 枚举 {@link TODO purchase_invoice_pay_way 对应的类}
     */
    private Integer paymentType;
    /**
     * 供应商联系人
     */
    private String supplieruser;
    /**
     * 供应商手机号
     */
    private String supplierphone;

}
