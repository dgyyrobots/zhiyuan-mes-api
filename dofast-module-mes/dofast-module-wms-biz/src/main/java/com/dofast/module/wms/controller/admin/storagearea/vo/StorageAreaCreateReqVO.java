package com.dofast.module.wms.controller.admin.storagearea.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 库位创建 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class StorageAreaCreateReqVO extends StorageAreaBaseVO {

    @Schema(description = "库位ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "6408")
    private Long id;
}
