package com.dofast.module.cmms.controller.admin.dvcheckmachinery.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 点检设备 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class DvCheckMachineryRespVO extends DvCheckMachineryBaseVO {

    @Schema(description = "流水号", requiredMode = Schema.RequiredMode.REQUIRED, example = "20509")
    private Long id;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

}
