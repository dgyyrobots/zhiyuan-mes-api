package com.dofast.module.finance.controller.admin.bpm.cashTrade.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Schema(description = "管理后台 - 财务流水创建 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CashTradeBpmCreateReqVO extends CashTradeBpmBaseVO {

}
