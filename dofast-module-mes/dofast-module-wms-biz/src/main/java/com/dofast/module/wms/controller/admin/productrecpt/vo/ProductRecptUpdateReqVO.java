package com.dofast.module.wms.controller.admin.productrecpt.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 产品入库录更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ProductRecptUpdateReqVO extends ProductRecptBaseVO {

    @Schema(description = "入库单ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "27945")
    @NotNull(message = "入库单ID不能为空")
    private Long id;

}
