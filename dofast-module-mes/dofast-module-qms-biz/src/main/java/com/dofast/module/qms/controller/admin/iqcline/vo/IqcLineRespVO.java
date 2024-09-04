package com.dofast.module.qms.controller.admin.iqcline.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 来料检验单行 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class IqcLineRespVO extends IqcLineBaseVO {

    @Schema(description = "记录ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "4092")
    private Long id;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

}
