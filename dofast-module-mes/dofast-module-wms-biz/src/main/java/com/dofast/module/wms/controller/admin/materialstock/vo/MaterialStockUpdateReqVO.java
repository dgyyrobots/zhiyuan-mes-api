package com.dofast.module.wms.controller.admin.materialstock.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 库存记录更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class MaterialStockUpdateReqVO extends MaterialStockBaseVO {

    @Schema(description = "事务ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "31507")
    @NotNull(message = "事务ID不能为空")
    private Long id;

}
