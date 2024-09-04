package com.dofast.module.mes.controller.admin.Autocodepart.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 编码生成规则组成 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class AutoCodePartRespVO extends AutoCodePartBaseVO {

    @Schema(description = "分段ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "18078")
    private Long id;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

}
