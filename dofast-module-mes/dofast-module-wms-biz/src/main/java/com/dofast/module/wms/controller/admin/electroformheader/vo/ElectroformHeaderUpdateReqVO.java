package com.dofast.module.wms.controller.admin.electroformheader.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 制版房领料单头更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ElectroformHeaderUpdateReqVO extends ElectroformHeaderBaseVO {

    @Schema(description = "制版领料单ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "23303")
    @NotNull(message = "制版领料单ID不能为空")
    private Long id;

}
