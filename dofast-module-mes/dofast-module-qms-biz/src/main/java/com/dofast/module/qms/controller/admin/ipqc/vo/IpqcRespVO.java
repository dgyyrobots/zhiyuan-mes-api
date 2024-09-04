package com.dofast.module.qms.controller.admin.ipqc.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 过程检验单 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class IpqcRespVO extends IpqcBaseVO {

    @Schema(description = "检验单ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1549")
    private Long id;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

}
