package com.dofast.module.mes.controller.admin.mdunitconverse.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 单位换算 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class MdUnitConverseRespVO extends MdUnitConverseBaseVO {

    @Schema(description = "单位ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "17696")
    private Long id;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

}
