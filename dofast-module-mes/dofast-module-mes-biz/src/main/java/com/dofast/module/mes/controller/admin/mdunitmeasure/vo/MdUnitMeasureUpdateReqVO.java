package com.dofast.module.mes.controller.admin.mdunitmeasure.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 单位更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class MdUnitMeasureUpdateReqVO extends MdUnitMeasureBaseVO {

    @Schema(description = "单位ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "12928")
    @NotNull(message = "单位ID不能为空")
    private Long id;

}
