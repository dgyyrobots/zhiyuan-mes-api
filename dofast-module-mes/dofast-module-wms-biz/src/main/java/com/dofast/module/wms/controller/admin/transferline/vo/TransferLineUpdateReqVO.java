package com.dofast.module.wms.controller.admin.transferline.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 转移单行更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class TransferLineUpdateReqVO extends TransferLineBaseVO {

    @Schema(description = "明细行ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "12002")
    @NotNull(message = "明细行ID不能为空")
    private Long id;

}
