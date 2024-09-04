package com.dofast.module.wms.controller.admin.storagearea.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 库位更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class StorageAreaUpdateReqVO extends StorageAreaBaseVO {

    @Schema(description = "库位ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "6408")
    @NotNull(message = "库位ID不能为空")
    private Long id;

}
