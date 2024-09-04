package com.dofast.module.channel.kndpojo.eorder;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Data
@ToString(callSuper = true)
@Schema(description = "快递鸟 电子面单 订单 Response VO")
public class KDNEOrderResOrder {
    @Schema(description = "订单编号", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull
    private String OrderCode;

    @Schema(description = "快递公司编码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull
    private String ShipperCode;

    @Schema(description = "快递单号", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull
    private String LogisticCode;

    @Schema(description = "大头笔", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String MarkDestination;

    @Schema(description = "签回单单号", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String SignWaybillCode;

    @Schema(description = "始发地区域编码", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String OriginCode;

    @Schema(description = "始发地/始发网点", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String OriginName;

    @Schema(description = "目的地区域编码", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String DestinatioCode;

    @Schema(description = "目的地/到达网点", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String DestinatioName;

    @Schema(description = "分拣编码", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String SortingCode;

    @Schema(description = "集包编码", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String PackageCode;

    @Schema(description = "集包地", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String PackageName;

    @Schema(description = "目的地分拨", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String DestinationAllocationCentre;

    @Schema(description = "配送产品类型", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Integer TransType;

    @Schema(description = "运输方式", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Integer TransportType;

    @Schema(description = "自行设计模板用", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String ShipperInfo;
}
