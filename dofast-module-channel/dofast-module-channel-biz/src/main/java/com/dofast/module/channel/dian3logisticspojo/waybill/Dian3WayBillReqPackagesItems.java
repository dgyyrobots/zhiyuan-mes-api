package com.dofast.module.channel.dian3logisticspojo.waybill;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Data
@ToString(callSuper = true)
@Schema(description = "Dian3 获取电子面单 包裹信息 商品信息 Request VO")
public class Dian3WayBillReqPackagesItems {
    @Schema(description = "名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull
    private String name;

    @Schema(description = "数量", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull
    private Integer num;
}
