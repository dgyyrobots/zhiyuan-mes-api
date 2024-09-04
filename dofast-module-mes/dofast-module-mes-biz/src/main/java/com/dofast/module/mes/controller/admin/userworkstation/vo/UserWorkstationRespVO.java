package com.dofast.module.mes.controller.admin.userworkstation.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 用户工作站绑定关系 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class UserWorkstationRespVO extends UserWorkstationBaseVO {

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

    @Schema(description = "记录ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "10150")
    private Long id;

}
