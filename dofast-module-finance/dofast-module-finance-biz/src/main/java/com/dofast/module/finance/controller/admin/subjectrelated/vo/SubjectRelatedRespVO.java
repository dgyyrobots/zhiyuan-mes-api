package com.dofast.module.finance.controller.admin.subjectrelated.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 收支科目 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class SubjectRelatedRespVO extends SubjectRelatedBaseVO {

    @Schema(description = "收支科目id", requiredMode = Schema.RequiredMode.REQUIRED, example = "15040")
    private Integer id;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDateTime createTime;

}
