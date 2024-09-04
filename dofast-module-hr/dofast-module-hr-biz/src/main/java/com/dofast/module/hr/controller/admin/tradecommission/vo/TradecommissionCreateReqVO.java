package com.dofast.module.hr.controller.admin.tradecommission.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 工资提成创建 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class TradecommissionCreateReqVO extends TradecommissionBaseVO {

}
