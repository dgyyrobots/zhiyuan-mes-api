package com.dofast.module.pro.controller.admin.workorderbom.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 生产工单BOM组成更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class WorkorderBomUpdateReqVO extends WorkorderBomBaseVO {

    @Schema(description = "行ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "29705")
    @NotNull(message = "行ID不能为空")
    private Long id;

}
