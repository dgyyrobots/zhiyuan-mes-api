package com.dofast.module.hr.controller.admin.tradecommission.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 工资提成更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class TradecommissionUpdateReqVO extends TradecommissionBaseVO {

    @Schema(description = "提成明细id", requiredMode = Schema.RequiredMode.REQUIRED, example = "12134")
    @NotNull(message = "提成明细id不能为空")
    private Integer id;

}
