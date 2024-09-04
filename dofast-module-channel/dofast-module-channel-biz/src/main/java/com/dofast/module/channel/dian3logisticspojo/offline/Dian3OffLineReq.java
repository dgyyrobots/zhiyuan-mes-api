package com.dofast.module.channel.dian3logisticspojo.offline;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Data
@ToString(callSuper = true)
@Schema(description = "Dian3 线下订单获取电子面单号 Request VO")
public class Dian3OffLineReq {
    @Schema(description = "点三分配给商家的AppKey", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull
    private String appKey;

    @Schema(description = "api名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull
    private String method;

    @Schema(description = "签名", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull
    private String sign;

    @Schema(description = "时间戳（秒）", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull
    private String timestamp;
}
