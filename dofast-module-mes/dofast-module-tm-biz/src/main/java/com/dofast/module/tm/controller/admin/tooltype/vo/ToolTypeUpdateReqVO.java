package com.dofast.module.tm.controller.admin.tooltype.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 工装夹具类型更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ToolTypeUpdateReqVO extends ToolTypeBaseVO {

    @Schema(description = "工装夹具类型ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "32475")
    @NotNull(message = "工装夹具类型ID不能为空")
    private Long id;

}
