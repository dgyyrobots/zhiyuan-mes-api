package com.dofast.module.wms.controller.admin.productsalseline.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 产品销售出库行创建 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ProductSalseLineCreateReqVO extends ProductSalseLineBaseVO {
    @Schema(description = "行ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "25111")
    private Long id;
}
