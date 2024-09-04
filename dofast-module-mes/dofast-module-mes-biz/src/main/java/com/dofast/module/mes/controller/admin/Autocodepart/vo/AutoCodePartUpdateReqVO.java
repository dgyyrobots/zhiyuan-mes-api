package com.dofast.module.mes.controller.admin.Autocodepart.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 编码生成规则组成更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class AutoCodePartUpdateReqVO extends AutoCodePartBaseVO {

    @Schema(description = "分段ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "18078")
    @NotNull(message = "分段ID不能为空")
    private Long id;

}
