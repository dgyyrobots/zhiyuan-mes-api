package com.dofast.module.wms.controller.admin.rtsalseline.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 产品销售退货行创建 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class RtSalseLineCreateReqVO extends RtSalseLineBaseVO {
    @Schema(description = "行ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "15022")
    private Long id;
}
