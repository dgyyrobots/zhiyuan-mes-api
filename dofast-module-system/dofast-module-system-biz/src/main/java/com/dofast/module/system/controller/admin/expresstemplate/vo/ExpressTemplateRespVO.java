package com.dofast.module.system.controller.admin.expresstemplate.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 运费模板 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ExpressTemplateRespVO extends ExpressTemplateBaseVO {

    @Schema(description = "运费模板编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "2858")
    private Long id;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDateTime createTime;

}
