package com.dofast.module.finance.controller.admin.bpm.cashFund.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Schema(description = "管理后台 - 财务退款创建 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CashFundBpmCreateReqVO extends CashFundBpmBaseVO {

}
