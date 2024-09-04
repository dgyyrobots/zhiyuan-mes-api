package com.dofast.module.wms.controller.admin.rtsalse.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 产品销售退货单创建 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class RtSalseCreateReqVO extends RtSalseBaseVO {
    @Schema(description = "退货单ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "31414")
    private Long id;
}
