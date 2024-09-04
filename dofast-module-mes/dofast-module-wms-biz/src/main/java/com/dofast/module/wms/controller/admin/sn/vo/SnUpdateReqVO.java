package com.dofast.module.wms.controller.admin.sn.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - SN码更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class SnUpdateReqVO extends SnBaseVO {

    @Schema(description = "SN码ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "23765")
    @NotNull(message = "SN码ID不能为空")
    private Long id;

}
