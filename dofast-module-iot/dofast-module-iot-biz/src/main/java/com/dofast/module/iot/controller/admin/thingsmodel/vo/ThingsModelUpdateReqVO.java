package com.dofast.module.iot.controller.admin.thingsmodel.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 物模型更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ThingsModelUpdateReqVO extends ThingsModelBaseVO {

    @Schema(description = "物模型ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "11336")
    @NotNull(message = "物模型ID不能为空")
    private Long id;

}
