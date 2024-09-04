package com.dofast.module.wms.controller.admin.transaction.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 库存事务更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class TransactionUpdateReqVO extends TransactionBaseVO {

    @Schema(description = "事务ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "46")
    @NotNull(message = "事务ID不能为空")
    private Long id;

}
