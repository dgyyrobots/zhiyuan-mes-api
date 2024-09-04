package com.dofast.module.trade.controller.admin.calculate.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 计价记录 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class CalculateRecordBaseVO {

    @Schema(description = "手机号", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "手机号不能为空")
    private String mobile;

    @Schema(description = "备注", requiredMode = Schema.RequiredMode.REQUIRED, example = "随便")
    @NotNull(message = "备注不能为空")
    private String remark;

}
