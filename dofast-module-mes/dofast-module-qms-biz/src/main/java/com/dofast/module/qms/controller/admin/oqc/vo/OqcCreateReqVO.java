package com.dofast.module.qms.controller.admin.oqc.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 出货检验单创建 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class OqcCreateReqVO extends OqcBaseVO {
    @Schema(description = "出货检验单ID", example = "43")
    private Long id;

    @Schema(description = "订单编号", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "订单编号不能为空")
    private String sourceCode;
}
