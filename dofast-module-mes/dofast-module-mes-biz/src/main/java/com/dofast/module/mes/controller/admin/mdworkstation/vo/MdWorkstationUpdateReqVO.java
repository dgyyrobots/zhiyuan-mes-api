package com.dofast.module.mes.controller.admin.mdworkstation.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 工作站更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class MdWorkstationUpdateReqVO extends MdWorkstationBaseVO {

    @Schema(description = "工作站ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "43")
    @NotNull(message = "工作站ID不能为空")
    private Long id;

}
