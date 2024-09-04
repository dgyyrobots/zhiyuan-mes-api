package com.dofast.module.report.controller.admin.PrintLog.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 打印日志 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class PrintLogBaseVO {

    @Schema(description = "打印记录编码",example = "AA")
    private String printCode;

    @Schema(description = "打印人", example = "张三")
    private String printName;

    @Schema(description = "打印类型", example = "1")
    private String printType;

}
