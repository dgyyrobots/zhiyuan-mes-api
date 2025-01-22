package com.dofast.module.wms.controller.admin.allocatedrecord.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 调拨单身记录更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class AllocatedRecordUpdateReqVO extends AllocatedRecordBaseVO {

    @Schema(description = "行ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "7532")
    @NotNull(message = "行ID不能为空")
    private Long id;

}
