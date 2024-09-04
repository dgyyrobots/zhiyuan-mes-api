package com.dofast.module.mes.controller.admin.qualityabnormity.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 品质异常信息更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class QualityAbnormityUpdateReqVO extends QualityAbnormityBaseVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "32128")
    @NotNull(message = "主键ID不能为空")
    private Long id;

}
