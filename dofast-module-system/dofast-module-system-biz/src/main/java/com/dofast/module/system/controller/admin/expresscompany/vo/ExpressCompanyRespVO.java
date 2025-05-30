package com.dofast.module.system.controller.admin.expresscompany.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 租户物流公司 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ExpressCompanyRespVO extends ExpressCompanyBaseVO {

    @Schema(description = "ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "31833")
    private Integer id;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

}
