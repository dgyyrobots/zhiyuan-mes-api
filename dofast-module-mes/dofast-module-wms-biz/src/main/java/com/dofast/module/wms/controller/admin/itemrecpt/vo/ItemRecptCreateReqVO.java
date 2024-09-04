package com.dofast.module.wms.controller.admin.itemrecpt.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 物料入库单创建 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ItemRecptCreateReqVO extends ItemRecptBaseVO {
    @Schema(description = "入库单ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "12747")
    private Long id;
}
