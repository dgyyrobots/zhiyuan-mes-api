package com.dofast.module.channel.kndpojo.eorder;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Data
@ToString(callSuper = true)
@Schema(description = "快递鸟 电子面单 寄件人 VO")
public class KDNEOrderSender {
    @Schema(description = "寄件人公司", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String Company;

    @Schema(description = "寄件人", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull
    private String Name;

    @Schema(description = "电话")
    private String Tel;

    @Schema(description = "手机")
    private String Mobile;

    @Schema(description = "发件省", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull
    private String ProvinceName;

    @Schema(description = "发件市", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull
    private String CityName;

    @Schema(description = "发件区/县", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull
    private String ExpAreaName;

    @Schema(description = "发件人详细地址", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull
    private String Address;

    @Schema(description = "发件地邮编，邮政/EMS必填", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String PostCode;
}
