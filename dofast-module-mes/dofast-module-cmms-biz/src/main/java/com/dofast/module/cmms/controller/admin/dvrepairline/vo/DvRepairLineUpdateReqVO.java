package com.dofast.module.cmms.controller.admin.dvrepairline.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 设备维修单行更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class DvRepairLineUpdateReqVO extends DvRepairLineBaseVO {

    @Schema(description = "行ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "24340")
    @NotNull(message = "行ID不能为空")
    private Long id;

}
