package com.dofast.module.channel.kndpojo.eorder;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@Schema(description = "快递鸟 电子面单 增值服务 VO")
public class KDNEOrderAddService {
    @Schema(description = "增值服务名称", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String Name;

    @Schema(description = "增值服务值", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String Value;

    @Schema(description = "客户标识", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String CustomerID;
}
