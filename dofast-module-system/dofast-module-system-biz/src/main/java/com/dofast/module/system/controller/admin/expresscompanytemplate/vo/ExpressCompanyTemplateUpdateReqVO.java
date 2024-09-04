package com.dofast.module.system.controller.admin.expresscompanytemplate.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Schema(description = "管理后台 - 系统物流公司更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ExpressCompanyTemplateUpdateReqVO extends ExpressCompanyTemplateBaseVO {

    @Schema(description = "公司ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "26855")
    @NotNull(message = "公司ID不能为空")
    private Integer companyId;

}
