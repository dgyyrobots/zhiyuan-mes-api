package com.dofast.module.report.controller.admin.PrintLog.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Schema(description = "管理后台 - 打印日志创建 Request VO")
@Data
@ToString(callSuper = true)
public class PrintLogCreateReqVO  {

    @Schema(description = "打印记录编码",example = "AA")
    @NotNull(message = "打印记录编码不能为空")
    private String printCode;

    @Schema(description = "打印人", example = "张三")
    @NotNull(message = "打印人不能为空")
    private String printName;

    @Schema(description = "打印类型", example = "1")
    @NotNull(message = "打印类型不能为空")
    private String printType;

}
