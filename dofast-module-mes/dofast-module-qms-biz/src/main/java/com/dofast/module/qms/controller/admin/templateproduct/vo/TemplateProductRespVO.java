package com.dofast.module.qms.controller.admin.templateproduct.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 检测模板-产品 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class TemplateProductRespVO extends TemplateProductBaseVO {

    @Schema(description = "记录ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1257")
    private Long id;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

}
