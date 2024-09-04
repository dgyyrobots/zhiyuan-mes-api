package com.dofast.module.wms.controller.admin.transaction.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 库存事务创建 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class TransactionCreateReqVO extends TransactionBaseVO {
    @Schema(description = "事务ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "46")
    private Long id;
}
