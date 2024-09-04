package com.dofast.module.purchase.controller.admin.refund.vo;

import java.time.LocalDate;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import javax.validation.constraints.*;

/**
 * 采购退货 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class RefundBaseVO {

    @Schema(description = "订单", example = "22411")
    private Integer purchaseId;

    @Schema(description = "流程实例的编号")
    private String processInstanceId;

    @Schema(description = "订单单号")
    private String purchaseOrderNum;

    @Schema(description = "入库单号")
    private String shipmentNum;

    @Schema(description = "退货总额")
    private BigDecimal returnBonus;

    @Schema(description = "是否退货")
    private Integer isReturn;

    @Schema(description = "供应商", example = "14077")
    private Integer supplierId;

    @Schema(description = "付款类型", example = "1")
    private Integer paymentType;

    @Schema(description = "供应商联系人")
    private String supplieruser;

    @Schema(description = "供应商手机号")
    private String supplierphone;

}
