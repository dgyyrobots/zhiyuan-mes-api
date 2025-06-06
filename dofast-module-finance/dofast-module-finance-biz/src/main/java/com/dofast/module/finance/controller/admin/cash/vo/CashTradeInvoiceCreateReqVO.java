package com.dofast.module.finance.controller.admin.cash.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 业务发票关联创建 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CashTradeInvoiceCreateReqVO extends CashTradeInvoiceBaseVO {

}
