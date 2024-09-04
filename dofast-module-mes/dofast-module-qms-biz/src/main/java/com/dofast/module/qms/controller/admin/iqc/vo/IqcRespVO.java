package com.dofast.module.qms.controller.admin.iqc.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 来料检验单 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class IqcRespVO extends IqcBaseVO {

    @Schema(description = "来料检验单ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "20687")
    private Long id;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

}
