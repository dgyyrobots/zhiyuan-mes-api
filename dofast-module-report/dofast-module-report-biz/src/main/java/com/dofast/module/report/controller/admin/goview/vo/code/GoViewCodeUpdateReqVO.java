package com.dofast.module.report.controller.admin.goview.vo.code;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.*;

@Schema(description = "管理后台 - GoView登录更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class GoViewCodeUpdateReqVO extends GoViewCodeBaseVO {

    @Schema(description = "记录ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "31748")
    @NotNull(message = "记录ID不能为空")
    private Long id;

    @Schema(description = "随机码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "随机码不能为空")
    private String code;

}
