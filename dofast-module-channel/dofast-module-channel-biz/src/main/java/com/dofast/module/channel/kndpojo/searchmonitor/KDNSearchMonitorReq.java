package com.dofast.module.channel.kndpojo.searchmonitor;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Data
@Schema(description = "快递鸟物流模块 轨迹查询 Request VO")
@ToString(callSuper = true)
public class KDNSearchMonitorReq {
    @Schema(description = "订单编号", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String OrderCode;

    @Schema(description = "快递公司编码", requiredMode = Schema.RequiredMode.REQUIRED, example = "SF")
    @NotNull(message = "快递公司编码不允许为空")
    private String ShipperCode;

    @Schema(description = "快递单号", requiredMode = Schema.RequiredMode.REQUIRED, example = "SF00003618100")
    @NotNull(message = "快递单号不允许为空")
    private String LogisticCode;

    @Schema(description = "寄件人或者收件人手机号后四位", requiredMode = Schema.RequiredMode.NOT_REQUIRED, example = "1234")
    private String CustomerName;
}
