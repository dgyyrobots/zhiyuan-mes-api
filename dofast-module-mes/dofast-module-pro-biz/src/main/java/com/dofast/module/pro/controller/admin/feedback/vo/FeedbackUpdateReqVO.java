package com.dofast.module.pro.controller.admin.feedback.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 生产报工记录更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class FeedbackUpdateReqVO extends FeedbackBaseVO {

    @Schema(description = "记录ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "9900")
    @NotNull(message = "记录ID不能为空")
    private Long id;

    @Schema(description = "报工班组成员列表")
    private List<Map<String, Object>> feedbackMemberList;

    @Schema(description = "报工缺陷项列表")
    private List<Map<String, Object>> processDefectList;

}
