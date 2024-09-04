package com.dofast.module.purchase.controller.admin.order.vo;

import java.time.LocalDate;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.alibaba.excel.annotation.ExcelProperty;
import com.dofast.framework.excel.core.annotations.DictFormat;
import com.dofast.framework.excel.core.convert.DictConvert;


/**
 * 采购订单 Excel VO
 *
 * @author 惠智造
 */
@Data
public class OrderExcelVO {
    @Schema(description = "流程实例的编号")
    private String processInstanceId;

    @ExcelProperty("供应商")
    private Integer supplierId;

    @ExcelProperty("供应商联系人")
    private String supplierContact;

    @ExcelProperty("供应商联系人电话")
    private String supplierPhone;

    @ExcelProperty("采购金额")
    private BigDecimal purchaseAmount;

    @ExcelProperty("ID")
    private Integer id;

    @ExcelProperty("采购单号")
    private String poNo;

    @ExcelProperty(value = "付款类型", converter = DictConvert.class)
    @DictFormat("purchase_invoice_pay_way") // TODO 代码优化：建议设置到对应的 XXXDictTypeConstants 枚举类中
    private Integer paymentType;

    @ExcelProperty(value = "退货状态", converter = DictConvert.class)
    @DictFormat("infra_boolean_string") // TODO 代码优化：建议设置到对应的 XXXDictTypeConstants 枚举类中
    private Integer returnGoods;

    @ExcelProperty(value = "审核类型", converter = DictConvert.class)
    @DictFormat("purchase_order_audit_status") // TODO 代码优化：建议设置到对应的 XXXDictTypeConstants 枚举类中
    private Integer processType;

    @ExcelProperty("备注")
    private String remarks;

    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}
