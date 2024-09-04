package com.dofast.module.pro.controller.admin.workrecord.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 上下工记录更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class WorkrecordUpdateReqVO extends WorkrecordBaseVO {

    @Schema(description = "记录ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "18820")
    @NotNull(message = "记录ID不能为空")
    private Long recordId;

}
