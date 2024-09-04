package com.dofast.module.qms.controller.admin.iqc.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 来料检验单更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class IqcUpdateReqVO extends IqcBaseVO {

    @Schema(description = "来料检验单ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "20687")
    @NotNull(message = "来料检验单ID不能为空")
    private Long id;

}
