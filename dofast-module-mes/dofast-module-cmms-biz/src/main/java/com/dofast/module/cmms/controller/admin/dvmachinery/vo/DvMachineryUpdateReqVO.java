package com.dofast.module.cmms.controller.admin.dvmachinery.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 设备台账更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class DvMachineryUpdateReqVO extends DvMachineryBaseVO {

    @Schema(description = "设备类型ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "24752")
    @NotNull(message = "设备类型ID不能为空")
    private Long id;

}
