package com.dofast.module.purchase.controller.admin.retreatGoods.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - ERP仓退单单身更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class RetreatGoodsUpdateReqVO extends RetreatGoodsBaseVO {

    @Schema(description = "id", requiredMode = Schema.RequiredMode.REQUIRED, example = "9012")
    @NotNull(message = "id不能为空")
    private Integer id;

}
