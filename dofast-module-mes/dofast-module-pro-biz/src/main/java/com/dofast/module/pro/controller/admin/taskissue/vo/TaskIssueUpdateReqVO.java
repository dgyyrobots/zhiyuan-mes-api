package com.dofast.module.pro.controller.admin.taskissue.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 生产任务投料更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class TaskIssueUpdateReqVO extends TaskIssueBaseVO {

    @Schema(description = "记录ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "20270")
    @NotNull(message = "记录ID不能为空")
    private Long id;

}
