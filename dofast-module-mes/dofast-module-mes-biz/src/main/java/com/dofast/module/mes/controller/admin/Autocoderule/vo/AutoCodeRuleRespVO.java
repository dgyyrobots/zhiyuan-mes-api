package com.dofast.module.mes.controller.admin.Autocoderule.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 编码生成规则 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class AutoCodeRuleRespVO extends AutoCodeRuleBaseVO {

    @Schema(description = "规则ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "19616")
    private Long id;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

}
