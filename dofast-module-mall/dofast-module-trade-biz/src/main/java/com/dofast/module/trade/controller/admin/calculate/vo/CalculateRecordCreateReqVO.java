package com.dofast.module.trade.controller.admin.calculate.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Schema(description = "管理后台 - 计价记录创建 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CalculateRecordCreateReqVO extends CalculateRecordBaseVO {

    @Schema(description = "数据", requiredMode = Schema.RequiredMode.REQUIRED, example = "14531")
    @NotNull(message = "数据不能为空")
    private Long recordId;

    @Schema(description = "类型", requiredMode = Schema.RequiredMode.REQUIRED, example = "22899")
    @NotNull(message = "类型不能为空")
    private Long typeId;

    @Schema(description = "结果", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "结果不能为空")
    private String result;

    @Schema(description = "操作系统", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "操作系统不能为空")
    private String os;

    @Schema(description = "客户端", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "客户端不能为空")
    private String client;

    @Schema(description = "设备信息", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "设备信息不能为空")
    private String device;

}
