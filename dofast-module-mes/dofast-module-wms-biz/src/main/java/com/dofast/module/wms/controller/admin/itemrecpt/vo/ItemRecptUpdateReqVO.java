package com.dofast.module.wms.controller.admin.itemrecpt.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 物料入库单更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ItemRecptUpdateReqVO extends ItemRecptBaseVO {

    @Schema(description = "入库单ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "12747")
    @NotNull(message = "入库单ID不能为空")
    private Long id;

}
