package com.dofast.module.report.controller.admin.goview.vo.code;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - GoView登录 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class GoViewCodeRespVO extends GoViewCodeBaseVO {

    @Schema(description = "记录ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "31748")
    private Long id;

    @Schema(description = "随机码", requiredMode = Schema.RequiredMode.REQUIRED)
    private String code;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

}
