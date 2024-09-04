package com.dofast.module.wms.controller.admin.productrecptline.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 产品入库记录表行创建 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ProductRecptLineCreateReqVO extends ProductRecptLineBaseVO {
    @Schema(description = "行ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "15543")
    private Long id;
}
