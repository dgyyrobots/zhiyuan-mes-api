package com.dofast.module.channel.dian3logisticspojo.logistics;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.ToString;

@Data
@Schema(description = "快递鸟物流模块 轨迹查询 Response VO")
@ToString(callSuper = true)
public class Dian3LogisticsRes {
    @Schema(description = "商家物流ID", requiredMode = Schema.RequiredMode.REQUIRED)
    private String id;

    @Schema(description = "商家物流编码", requiredMode = Schema.RequiredMode.REQUIRED)
    private String code;

    @Schema(description = "商家物流名称", requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;

    @Schema(description = "商家物流状态", requiredMode = Schema.RequiredMode.REQUIRED)
    private String status;

    @Schema(description = "商家物流匹配的物流单打印模板", requiredMode = Schema.RequiredMode.REQUIRED)
    private Dian3LogisticsResPrintTemplate printTemplate;

    @Schema(description = "商家物流鉴权信息(部分非敏感信息)", requiredMode = Schema.RequiredMode.REQUIRED)
    private Dian3LogisticsResAuthInfo authInfo;

    @Schema(description = "商家物流关联的电子面单平台", requiredMode = Schema.RequiredMode.REQUIRED)
    private String ewPlatformType;

    @Schema(description = "点三物流/快递 公司编码", requiredMode = Schema.RequiredMode.REQUIRED)
    private String logisticsCode;

    @Schema(description = "点三物流/快递 公司名称。即D3商家中心-商家物流页面中的 物流类型。",
            requiredMode = Schema.RequiredMode.REQUIRED)
    private String logisticsName;
}
