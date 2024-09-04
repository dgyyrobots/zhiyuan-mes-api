package com.dofast.module.report.controller.admin.PrintLog.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Schema(description = "管理后台 - 打印日志更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class PrintLogUpdateReqVO extends PrintLogBaseVO {

    @Schema(description = "打印记录ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "9229")
    @NotNull(message = "打印记录ID不能为空")
    private Long id;

}
