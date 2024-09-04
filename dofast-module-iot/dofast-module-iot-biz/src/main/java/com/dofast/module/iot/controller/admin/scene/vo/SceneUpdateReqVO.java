package com.dofast.module.iot.controller.admin.scene.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 场景联动更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class SceneUpdateReqVO extends SceneBaseVO {

    @Schema(description = "场景ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "26116")
    @NotNull(message = "场景ID不能为空")
    private Long id;

}
