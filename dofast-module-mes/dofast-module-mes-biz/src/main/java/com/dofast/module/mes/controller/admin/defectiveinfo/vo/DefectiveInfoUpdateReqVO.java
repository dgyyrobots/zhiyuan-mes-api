package com.dofast.module.mes.controller.admin.defectiveinfo.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 不良品信息管理更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class DefectiveInfoUpdateReqVO extends DefectiveInfoBaseVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "26830")
    @NotNull(message = "主键ID不能为空")
    private Long id;

}
