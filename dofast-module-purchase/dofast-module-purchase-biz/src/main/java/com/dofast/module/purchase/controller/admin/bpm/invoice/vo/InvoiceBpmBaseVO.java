package com.dofast.module.purchase.controller.admin.bpm.invoice.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 采购入库单 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class InvoiceBpmBaseVO {


    @Schema(description = "采购单号")
    private String purchaseOrderNum;

    @Schema(description = "流程实例的编号")
    private String processInstanceId;

    @Schema(description = "入库单号")
    private String shipmentNum;

    @Schema(description = "订单", example = "12272")
    private Integer purchaseId;

    @Schema(description = "供应商", example = "31852")
    private Integer supplierId;

    @Schema(description = "付款类型(0应付账款1现金付款2预付款)", example = "2")
    private Integer paymentType;

    @Schema(description = "有无退货")
    private Integer isReturn;

    @Schema(description = "供应商联系人")
    private String supplieruser;

    @Schema(description = "供应商手机号")
    private String supplierphone;

    @Schema(description = "采购总价", example = "11462")
    private Object totalPrice;

    @Schema(description = "是否入库")
    private Integer isWarehousing;

}
