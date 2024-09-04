package com.dofast.module.finance.controller.admin.bpm.cashInvoice.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Schema(description = "管理后台 - 发票信息创建 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CashInvoiceBpmCreateReqVO extends CashInvoiceBpmBaseVO {

}
