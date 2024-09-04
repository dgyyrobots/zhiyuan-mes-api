package com.dofast.module.channel.dian3logisticspojo.waybill;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@ToString(callSuper = true)
@Schema(description = "Dian3 获取电子面单 Request VO")
public class Dian3WayBillReq {
    @Schema(description = "OMS商家物流编码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull
    private String cpCode;

    @Schema(description = "发件人信息", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Dian3WayBillReqSend send;

    @Schema(description = "包裹信息", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull
    private List<Dian3WayBillReqPackages> packages;

    @Schema(description = "平台标准打印模板的URL", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String templateUrl;
}
