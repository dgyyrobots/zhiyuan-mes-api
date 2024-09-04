package com.dofast.module.system.controller.admin.systemconfig.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 参数配置 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class SystemConfigRespVO extends SystemConfigBaseVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "9215")
    private Integer id;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

}
