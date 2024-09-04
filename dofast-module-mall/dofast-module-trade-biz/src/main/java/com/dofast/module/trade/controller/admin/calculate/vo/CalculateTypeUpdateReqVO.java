package com.dofast.module.trade.controller.admin.calculate.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Schema(description = "管理后台 - 计价类型更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CalculateTypeUpdateReqVO extends CalculateTypeBaseVO {

    @Schema(description = "ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "10691")
    @NotNull(message = "ID不能为空")
    private Integer id;

    @Schema(description = "备注", example = "随便")
    private String description;

}
