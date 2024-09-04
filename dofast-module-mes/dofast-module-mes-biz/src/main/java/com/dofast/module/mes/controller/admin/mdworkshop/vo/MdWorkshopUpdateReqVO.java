package com.dofast.module.mes.controller.admin.mdworkshop.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 车间更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class MdWorkshopUpdateReqVO extends MdWorkshopBaseVO {

    @Schema(description = "车间ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "22987")
    @NotNull(message = "车间ID不能为空")
    private Long id;

}
