package com.dofast.module.mes.controller.admin.electroplatelog.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 制版房记录更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ElectroplateLogUpdateReqVO extends ElectroplateLogBaseVO {

    @Schema(description = "ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "17965")
    @NotNull(message = "ID不能为空")
    private Long id;

}
