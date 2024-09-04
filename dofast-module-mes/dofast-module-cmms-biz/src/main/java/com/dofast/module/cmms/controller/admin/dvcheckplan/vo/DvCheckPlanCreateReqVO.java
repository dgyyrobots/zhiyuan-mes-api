package com.dofast.module.cmms.controller.admin.dvcheckplan.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 设备点检保养计划头创建 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class DvCheckPlanCreateReqVO extends DvCheckPlanBaseVO {
    @Schema(description = "计划ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "25512")
    private Long id;
}
