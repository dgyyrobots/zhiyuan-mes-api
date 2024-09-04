package com.dofast.module.channel.dian3logisticspojo.logistics;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.ToString;

@Data
@Schema(description = "快递鸟物流模块 轨迹查询 物流单打印模板 Response VO")
@ToString(callSuper = true)
public class Dian3LogisticsResPrintTemplate {
    @Schema(description = "点三物流单打印模板ID", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer id;

    @Schema(description = "点三物流单打印模板名称", requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;

    @Schema(description = "点三物流单打印模板来源", requiredMode = Schema.RequiredMode.REQUIRED)
    private String templateSource;

    @Schema(description = "三方标准打印模板URL，如果存在则返回", requiredMode = Schema.RequiredMode.REQUIRED)
    private String standardTemplateUrl;
}
