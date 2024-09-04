package com.dofast.module.channel.controller.admin.remark.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 订单备注 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class RemarkRespVO extends RemarkBaseVO {

    @Schema(description = "自增ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "23860")
    private Long id;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDateTime createTime;

}
