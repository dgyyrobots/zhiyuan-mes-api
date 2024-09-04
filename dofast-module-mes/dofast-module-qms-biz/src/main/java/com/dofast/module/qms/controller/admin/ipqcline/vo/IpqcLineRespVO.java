package com.dofast.module.qms.controller.admin.ipqcline.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 过程检验单行 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class IpqcLineRespVO extends IpqcLineBaseVO {

    @Schema(description = "记录ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "26211")
    private Long id;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

}
