package com.dofast.module.channel.kndpojo.eorder;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@Schema(description = "快递鸟 电子面单 收件人 VO")
public class KDNEOrderReciver {
    @Schema(description = "商品编码", requiredMode = Schema.RequiredMode.REQUIRED)
    private String Name;

    @Schema(description = "电话")
    private String Tel;

    @Schema(description = "手机")
    private String Mobile;

    @Schema(description = "收件省", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull
    private String ProvinceName;

    @Schema(description = "收件市", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull
    private String CityName;

    @Schema(description = "收件区/县", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull
    private String ExpAreaName;

    @Schema(description = "收件人详细地址", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull
    private String Address;

    @Schema(description = "收件地邮编，邮政/EMS必填", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String PostCode;

    @Schema(description = "收件人公司", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String Company;
}
