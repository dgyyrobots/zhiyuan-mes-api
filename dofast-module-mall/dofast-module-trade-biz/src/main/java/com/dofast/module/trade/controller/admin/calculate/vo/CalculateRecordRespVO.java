package com.dofast.module.trade.controller.admin.calculate.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 计价记录 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CalculateRecordRespVO extends CalculateRecordBaseVO {

    @Schema(description = "ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "28080")
    private Long id;

    @Schema(description = "数据", requiredMode = Schema.RequiredMode.REQUIRED, example = "14531")
    private Long recordId;

    @Schema(description = "类型", requiredMode = Schema.RequiredMode.REQUIRED, example = "22899")
    private Long typeId;

    @Schema(description = "结果", requiredMode = Schema.RequiredMode.REQUIRED)
    private String result;

    @Schema(description = "操作系统", requiredMode = Schema.RequiredMode.REQUIRED)
    private String os;

    @Schema(description = "客户端", requiredMode = Schema.RequiredMode.REQUIRED)
    private String client;

    @Schema(description = "设备信息", requiredMode = Schema.RequiredMode.REQUIRED)
    private String device;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDateTime createTime;

}
