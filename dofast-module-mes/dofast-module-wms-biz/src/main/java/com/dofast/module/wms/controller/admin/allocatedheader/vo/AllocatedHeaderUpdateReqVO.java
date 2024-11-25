package com.dofast.module.wms.controller.admin.allocatedheader.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 调拨单头更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class AllocatedHeaderUpdateReqVO extends AllocatedHeaderBaseVO {

    @Schema(description = "调拨单ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "15487")
    @NotNull(message = "调拨单ID不能为空")
    private Long id;

    private List<Map<String, Object>> bomList;

}
