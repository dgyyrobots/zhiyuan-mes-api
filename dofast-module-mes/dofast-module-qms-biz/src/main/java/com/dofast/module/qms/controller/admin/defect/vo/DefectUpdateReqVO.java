package com.dofast.module.qms.controller.admin.defect.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 常见缺陷更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class DefectUpdateReqVO extends DefectBaseVO {

    @Schema(description = "缺陷ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "16830")
    @NotNull(message = "缺陷ID不能为空")
    private Long id;

}
