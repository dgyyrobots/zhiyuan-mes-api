package com.dofast.module.purchase.controller.admin.order.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * 采购订单 Excel VO
 *
 * @author 惠智造
 */
@Data
public class OrderExcelVO {

    @ExcelProperty("供应商联系人")
    private String supplierContact;

    @ExcelProperty("供应商联系人电话")
    private String supplierPhone;

    @ExcelProperty("采购金额")
    private BigDecimal purchaseAmount;

    @ExcelProperty("采购单号")
    private String poNo;

    @ExcelProperty("备注")
    private String remarks;

    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

    @ExcelProperty("资金账户id")
    private Integer id;

    @ExcelProperty("流程实例的编号")
    private String processInstanceId;

    @ExcelProperty("供应商id")
    private Integer supplierId;

    @ExcelProperty("付款类型(0应付账款1现金付款2预付款)")
    private Integer paymentType;

    @ExcelProperty("退货状态(0有1无)")
    private Integer returnGoods;

    @ExcelProperty("审核类型（0已审核，1未审核，3采购入库，3确认入库）")
    private Integer processType;

    @ExcelProperty("母批次号")
    private String parentBatchCode;

    @ExcelProperty("流水号")
    private String serial;

}
