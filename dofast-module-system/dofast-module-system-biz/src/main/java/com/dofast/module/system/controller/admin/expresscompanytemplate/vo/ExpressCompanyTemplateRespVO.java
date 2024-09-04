package com.dofast.module.system.controller.admin.expresscompanytemplate.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 系统物流公司 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ExpressCompanyTemplateRespVO extends ExpressCompanyTemplateBaseVO {

    @Schema(description = "公司ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "26855")
    private Integer companyId;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

}
