package com.dofast.module.qms.controller.admin.oqc.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 出货检验单更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class OqcUpdateReqVO extends OqcBaseVO {

    @Schema(description = "出货检验单ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "43")
    @NotNull(message = "出货检验单ID不能为空")
    private Long id;

    @Schema(description = "订单编号", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "订单编号不能为空")
    private String sourceCode;

}
