package com.dofast.module.cmms.controller.admin.dvsubject.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 设备点检保养项目 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class DvSubjectRespVO extends DvSubjectBaseVO {

    @Schema(description = "项目ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "16116")
    private Long id;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

}
