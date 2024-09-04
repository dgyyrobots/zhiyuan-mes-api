package com.dofast.module.wms.controller.admin.itemrecptline.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 物料入库单行创建 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ItemRecptLineCreateReqVO extends ItemRecptLineBaseVO {
    @Schema(description = "行ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "26393")
    private Long id;
}
