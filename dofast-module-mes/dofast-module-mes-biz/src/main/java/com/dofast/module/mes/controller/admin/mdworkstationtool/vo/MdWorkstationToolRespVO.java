package com.dofast.module.mes.controller.admin.mdworkstationtool.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 工装夹具资源 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class MdWorkstationToolRespVO extends MdWorkstationToolBaseVO {

    @Schema(description = "记录ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "14883")
    private Long id;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

}
