package com.dofast.module.purchase.controller.admin.order.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import javax.validation.constraints.*;

/**
 * 采购订单 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class OrderBaseVO {

    @Schema(description = "供应商联系人")
    private String supplierContact;

    @Schema(description = "供应商联系人电话")
    private String supplierPhone;

    @Schema(description = "采购金额")
    private BigDecimal purchaseAmount;

    @Schema(description = "采购单号")
    private String poNo;

    @Schema(description = "备注")
    private String remarks;

    @Schema(description = "流程实例的编号", example = "25492")
    private String processInstanceId;

    @Schema(description = "供应商id", example = "9696")
    private Integer supplierId;

    @Schema(description = "付款类型(0应付账款1现金付款2预付款)", example = "1")
    private Integer paymentType;

    @Schema(description = "退货状态(0有1无)")
    private Integer returnGoods;

    @Schema(description = "审核类型（0已审核，1未审核，3采购入库，3确认入库）", example = "2")
    private Integer processType;

    @Schema(description = "母批次号")
    private String parentBatchCode;

    @Schema(description = "流水号")
    private String serial;

    @Schema(description = "供应商编码")
    private String supplierCode;


}
