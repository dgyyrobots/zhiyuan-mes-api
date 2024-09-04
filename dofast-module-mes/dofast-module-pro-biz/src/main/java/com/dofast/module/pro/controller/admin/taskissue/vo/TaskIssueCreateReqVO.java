package com.dofast.module.pro.controller.admin.taskissue.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 生产任务投料创建 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class TaskIssueCreateReqVO extends TaskIssueBaseVO {
    @Schema(description = "记录ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "20270")
    private Long id;

}
