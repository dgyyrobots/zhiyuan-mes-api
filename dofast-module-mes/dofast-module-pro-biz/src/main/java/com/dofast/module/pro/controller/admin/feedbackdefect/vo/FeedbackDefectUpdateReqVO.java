package com.dofast.module.pro.controller.admin.feedbackdefect.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 报工缺陷更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class FeedbackDefectUpdateReqVO extends FeedbackDefectBaseVO {

    @Schema(description = "ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "20233")
    @NotNull(message = "ID不能为空")
    private Long id;

}
