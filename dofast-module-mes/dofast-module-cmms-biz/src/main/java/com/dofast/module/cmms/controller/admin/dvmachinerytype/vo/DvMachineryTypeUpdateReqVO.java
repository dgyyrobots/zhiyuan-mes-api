package com.dofast.module.cmms.controller.admin.dvmachinerytype.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 设备类型更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class DvMachineryTypeUpdateReqVO extends DvMachineryTypeBaseVO {

    @Schema(description = "设备类型ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "22250")
    @NotNull(message = "设备类型ID不能为空")
    private Long machineryTypeId;

}
