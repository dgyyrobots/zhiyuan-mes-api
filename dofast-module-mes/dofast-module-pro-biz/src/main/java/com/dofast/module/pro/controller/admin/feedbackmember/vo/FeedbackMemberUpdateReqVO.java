package com.dofast.module.pro.controller.admin.feedbackmember.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 报工班组人员更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class FeedbackMemberUpdateReqVO extends FeedbackMemberBaseVO {

    @Schema(description = "ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "31837")
    @NotNull(message = "ID不能为空")
    private Long id;

}
