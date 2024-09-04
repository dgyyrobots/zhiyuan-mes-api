package com.dofast.module.purchase.controller.admin.invoice.vo;

import java.time.LocalDate;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;

import com.alibaba.excel.annotation.ExcelProperty;
import com.dofast.framework.excel.core.annotations.DictFormat;
import com.dofast.framework.excel.core.convert.DictConvert;


/**
 * 采购入库单 Excel VO
 *
 * @author 惠智造
 */
@Data
public class InvoiceExcelVO {

    @ExcelProperty("id")
    private Integer id;

    @Schema(description = "流程实例的编号")
    private String processInstanceId;

    @ExcelProperty("采购单号")
    private String purchaseOrderNum;

    @ExcelProperty("入库单号")
    private String shipmentNum;

    @ExcelProperty("订单")
    private Integer purchaseId;

    @ExcelProperty("供应商")
    private Integer supplierId;

    @ExcelProperty(value = "付款类型(0应付账款1现金付款2预付款)", converter = DictConvert.class)
    @DictFormat("purchase_invoice_pay_way") // TODO 代码优化：建议设置到对应的 XXXDictTypeConstants 枚举类中
    private Integer paymentType;

    @ExcelProperty(value = "有无退货", converter = DictConvert.class)
    @DictFormat("infra_boolean_string") // TODO 代码优化：建议设置到对应的 XXXDictTypeConstants 枚举类中
    private Integer isReturn;

    @ExcelProperty("供应商联系人")
    private String supplieruser;

    @ExcelProperty("供应商手机号")
    private String supplierphone;

    @ExcelProperty("采购总价")
    private Object totalPrice;

    @ExcelProperty(value = "是否入库", converter = DictConvert.class)
    @DictFormat("infra_boolean_string") // TODO 代码优化：建议设置到对应的 XXXDictTypeConstants 枚举类中
    private Integer isWarehousing;

    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}
