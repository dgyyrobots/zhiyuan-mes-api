package com.dofast.module.finance.controller.admin.bpm.cashInvoiceDetail.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Schema(description = "管理后台 - 财务发票明细创建 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CashInvoiceDetailBpmCreateReqVO extends CashInvoiceDetailBpmBaseVO {

}
