package com.dofast.module.pro.controller.admin.workorder.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 生产工单更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class WorkorderUpdateReqVO extends WorkorderBaseVO {

    @Schema(description = "工单ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "20735")
    @NotNull(message = "工单ID不能为空")
    private Long id;

}
