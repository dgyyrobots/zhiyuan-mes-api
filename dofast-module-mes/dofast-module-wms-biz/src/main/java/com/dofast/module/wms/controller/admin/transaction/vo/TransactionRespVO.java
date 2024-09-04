package com.dofast.module.wms.controller.admin.transaction.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 库存事务 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class TransactionRespVO extends TransactionBaseVO {
    /**
     * 是否检查库存量
     * 如果设置为True则库存不允许为负
     */
    private boolean storageCheckFlag;
    @Schema(description = "事务ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "46")
    private Long id;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

}
