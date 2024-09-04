package com.dofast.module.tm.controller.admin.tool.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 工装夹具清单更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ToolUpdateReqVO extends ToolBaseVO {

    @Schema(description = "工装夹具ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "27171")
    @NotNull(message = "工装夹具ID不能为空")
    private Long id;

}
