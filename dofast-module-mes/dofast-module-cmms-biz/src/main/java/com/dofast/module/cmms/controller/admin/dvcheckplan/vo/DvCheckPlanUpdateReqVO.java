package com.dofast.module.cmms.controller.admin.dvcheckplan.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 设备点检保养计划头更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class DvCheckPlanUpdateReqVO extends DvCheckPlanBaseVO {

    @Schema(description = "计划ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "25512")
    @NotNull(message = "计划ID不能为空")
    private Long id;

}
