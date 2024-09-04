package com.dofast.module.mes.controller.admin.mdworkshop.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 车间 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class MdWorkshopRespVO extends MdWorkshopBaseVO {

    @Schema(description = "车间ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "22987")
    private Long id;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

}
