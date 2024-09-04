package com.dofast.module.wms.controller.admin.storagelocation.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 库区更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class StorageLocationUpdateReqVO extends StorageLocationBaseVO {

    @Schema(description = "库区ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "18526")
    @NotNull(message = "库区ID不能为空")
    private Long id;

}
