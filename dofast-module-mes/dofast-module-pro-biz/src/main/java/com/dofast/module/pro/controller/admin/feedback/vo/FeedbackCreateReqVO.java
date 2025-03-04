package com.dofast.module.pro.controller.admin.feedback.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 生产报工记录创建 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class FeedbackCreateReqVO extends FeedbackBaseVO {
    @Schema(description = "报工ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "20491")
    private Long id;

    @Schema(description = "任务ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "20491")
    private Long taskId;

    @Schema(description = "报工班组成员列表")
    private List<Map<String, Object>> feedbackMemberList;

    @Schema(description = "报工缺陷项列表")
    private List<Map<String, Object>> processDefectList;
}
