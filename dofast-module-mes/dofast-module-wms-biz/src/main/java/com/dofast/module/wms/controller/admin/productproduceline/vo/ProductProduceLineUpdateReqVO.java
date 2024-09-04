package com.dofast.module.wms.controller.admin.productproduceline.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 产品产出记录表行更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ProductProduceLineUpdateReqVO extends ProductProduceLineBaseVO {

    @Schema(description = "行ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "26316")
    @NotNull(message = "行ID不能为空")
    private Long id;

}
