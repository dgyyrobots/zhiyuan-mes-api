package com.dofast.module.pro.controller.admin.transconsume.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 物料消耗记录更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class TransConsumeUpdateReqVO extends TransConsumeBaseVO {

    @Schema(description = "记录ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "6107")
    @NotNull(message = "记录ID不能为空")
    private Long id;

}
