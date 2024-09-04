package com.dofast.module.wms.controller.admin.barcode.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 条码清单更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class BarcodeUpdateReqVO extends BarcodeBaseVO {

    @Schema(description = "条码ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "14867")
    @NotNull(message = "条码ID不能为空")
    private Long id;

}
