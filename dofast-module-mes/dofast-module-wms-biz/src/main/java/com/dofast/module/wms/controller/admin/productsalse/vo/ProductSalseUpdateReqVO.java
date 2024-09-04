package com.dofast.module.wms.controller.admin.productsalse.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 销售出库单更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ProductSalseUpdateReqVO extends ProductSalseBaseVO {

    @Schema(description = "出库单ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "18915")
    @NotNull(message = "出库单ID不能为空")
    private Long id;

}
