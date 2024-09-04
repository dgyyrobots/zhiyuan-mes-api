package com.dofast.module.wms.controller.admin.barcodeconfig.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 条码配置更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class BarcodeConfigUpdateReqVO extends BarcodeConfigBaseVO {

    @Schema(description = "配置ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "24520")
    @NotNull(message = "配置ID不能为空")
    private Long id;

}
