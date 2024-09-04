package com.dofast.module.wms.controller.admin.productsalse.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 销售出库单创建 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ProductSalseCreateReqVO extends ProductSalseBaseVO {
    @Schema(description = "出库单ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "18915")
    private Long id;
}
