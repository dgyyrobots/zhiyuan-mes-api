package com.dofast.module.wms.controller.admin.warehouse.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 仓库创建 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class WarehouseCreateReqVO extends WarehouseBaseVO {
    @Schema(description = "仓库ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "8545")
    private Long id;
}
