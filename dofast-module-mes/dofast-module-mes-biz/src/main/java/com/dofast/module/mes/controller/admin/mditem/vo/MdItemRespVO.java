package com.dofast.module.mes.controller.admin.mditem.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 物料产品 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class MdItemRespVO extends MdItemBaseVO {

    @Schema(description = "产品物料ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "27681")
    private Long id;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;



}
