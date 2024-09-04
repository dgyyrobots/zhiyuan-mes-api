package com.dofast.module.mes.controller.admin.mdclient.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 客户 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class MdClientRespVO extends MdClientBaseVO {

    @Schema(description = "客户ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "6498")
    private Long id;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

}
