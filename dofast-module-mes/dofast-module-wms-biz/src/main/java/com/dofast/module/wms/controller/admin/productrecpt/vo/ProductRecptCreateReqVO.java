package com.dofast.module.wms.controller.admin.productrecpt.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 产品入库录创建 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ProductRecptCreateReqVO extends ProductRecptBaseVO {
    @Schema(description = "入库单ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "27945")
    private Long id;
}
