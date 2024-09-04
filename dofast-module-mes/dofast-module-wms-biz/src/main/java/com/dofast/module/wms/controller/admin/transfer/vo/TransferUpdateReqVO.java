package com.dofast.module.wms.controller.admin.transfer.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 转移单更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class TransferUpdateReqVO extends TransferBaseVO {

    @Schema(description = "转移单ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "8558")
    @NotNull(message = "转移单ID不能为空")
    private Long id;

}
