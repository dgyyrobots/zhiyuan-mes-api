package com.dofast.module.mes.controller.admin.mditemtype.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 物料产品分类 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class MdItemTypeRespVO extends MdItemTypeBaseVO {

    @Schema(description = "产品物料类型ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "2895")
    private Long id;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

}
