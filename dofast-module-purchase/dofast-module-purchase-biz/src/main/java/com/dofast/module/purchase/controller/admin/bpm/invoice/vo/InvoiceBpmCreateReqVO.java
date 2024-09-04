package com.dofast.module.purchase.controller.admin.bpm.invoice.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Schema(description = "管理后台 - 采购入库单创建 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class InvoiceBpmCreateReqVO extends InvoiceBpmBaseVO {

}
