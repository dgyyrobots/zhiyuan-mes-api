package com.dofast.module.wms.controller.admin.itemconsumeline.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 物料消耗记录行更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ItemConsumeLineUpdateReqVO extends ItemConsumeLineBaseVO {

    @Schema(description = "行ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "15975")
    @NotNull(message = "行ID不能为空")
    private Long id;

}
