package com.dofast.module.wms.controller.admin.rtsalse.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 产品销售退货单更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class RtSalseUpdateReqVO extends RtSalseBaseVO {

    @Schema(description = "退货单ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "31414")
    @NotNull(message = "退货单ID不能为空")
    private Long id;

}
