package com.dofast.module.mes.controller.admin.mdworkstationmachine.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 设备资源 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class MdWorkstationMachineRespVO extends MdWorkstationMachineBaseVO {

    @Schema(description = "记录ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "25735")
    private Long id;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

}
