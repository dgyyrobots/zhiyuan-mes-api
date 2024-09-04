package com.dofast.module.wms.controller.admin.materialstock.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 库存记录创建 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class MaterialStockCreateReqVO extends MaterialStockBaseVO {
    @Schema(description = "事务ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "31507")
    private Long id;
}
