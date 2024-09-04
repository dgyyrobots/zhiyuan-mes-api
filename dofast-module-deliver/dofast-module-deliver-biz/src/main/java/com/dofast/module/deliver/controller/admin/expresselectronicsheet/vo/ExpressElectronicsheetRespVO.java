package com.dofast.module.deliver.controller.admin.expresselectronicsheet.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 电子面单 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ExpressElectronicsheetRespVO extends ExpressElectronicsheetBaseVO {

    @Schema(description = "ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "21448")
    private Long id;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

}
