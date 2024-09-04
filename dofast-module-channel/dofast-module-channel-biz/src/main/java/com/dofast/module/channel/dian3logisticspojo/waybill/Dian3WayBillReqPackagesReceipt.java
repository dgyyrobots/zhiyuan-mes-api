package com.dofast.module.channel.dian3logisticspojo.waybill;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Data
@ToString(callSuper = true)
@Schema(description = "Dian3 获取电子面单 包裹信息 收件人信息 Request VO")
public class Dian3WayBillReqPackagesReceipt {
    @Schema(description = "省", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull
    private String province;

    @Schema(description = "市", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull
    private String city;

    @Schema(description = "县区", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull
    private String district;

    @Schema(description = "街道", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull
    private String town;

    @Schema(description = "详细地址", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull
    private String detail;

    @Schema(description = "姓名", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull
    private String name;

    @Schema(description = "手机号码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull
    private String mobile;

    @Schema(description = "固定号码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull
    private String phone;
}
