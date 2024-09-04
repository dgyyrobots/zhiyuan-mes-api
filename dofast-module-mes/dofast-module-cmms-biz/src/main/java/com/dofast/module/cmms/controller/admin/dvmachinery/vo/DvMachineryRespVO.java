package com.dofast.module.cmms.controller.admin.dvmachinery.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 设备台账 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class DvMachineryRespVO extends DvMachineryBaseVO {

    @Schema(description = "设备类型ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "24752")
    private Long id;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

}
