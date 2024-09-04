package com.dofast.module.wms.controller.admin.productsalseline.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 产品销售出库行更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ProductSalseLineUpdateReqVO extends ProductSalseLineBaseVO {

    @Schema(description = "行ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "25111")
    @NotNull(message = "行ID不能为空")
    private Long id;

}
