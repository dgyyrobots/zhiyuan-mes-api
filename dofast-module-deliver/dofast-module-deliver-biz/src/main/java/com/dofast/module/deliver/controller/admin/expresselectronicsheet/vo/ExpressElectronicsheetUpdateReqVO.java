package com.dofast.module.deliver.controller.admin.expresselectronicsheet.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 电子面单更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ExpressElectronicsheetUpdateReqVO extends ExpressElectronicsheetBaseVO {

    @Schema(description = "ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "21448")
    @NotNull(message = "ID不能为空")
    private Long id;

}
