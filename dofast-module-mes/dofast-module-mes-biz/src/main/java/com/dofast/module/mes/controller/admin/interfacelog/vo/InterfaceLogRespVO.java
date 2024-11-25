package com.dofast.module.mes.controller.admin.interfacelog.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 接口操作日志 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class InterfaceLogRespVO extends InterfaceLogBaseVO {

    @Schema(description = "ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "14566")
    private Long id;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

}
