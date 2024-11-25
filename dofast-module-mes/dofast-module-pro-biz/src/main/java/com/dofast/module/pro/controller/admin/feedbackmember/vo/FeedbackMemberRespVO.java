package com.dofast.module.pro.controller.admin.feedbackmember.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 报工班组人员 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class FeedbackMemberRespVO extends FeedbackMemberBaseVO {

    @Schema(description = "ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "31837")
    private Long id;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDateTime createTime;

}
