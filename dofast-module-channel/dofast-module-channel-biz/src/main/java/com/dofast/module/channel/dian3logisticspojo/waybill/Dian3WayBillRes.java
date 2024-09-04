package com.dofast.module.channel.dian3logisticspojo.waybill;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@Schema(description = "Dian3 获取电子面单 Response VO")
public class Dian3WayBillRes {
    @Schema(description = "外部单据编号", requiredMode = Schema.RequiredMode.REQUIRED)
    private String outerCode;

    @Schema(description = "结果", requiredMode = Schema.RequiredMode.REQUIRED)
    private Boolean result;

    @Schema(description = "物流单号", requiredMode = Schema.RequiredMode.REQUIRED)
    private String waybillCode;

    @Schema(description = "失败原因", requiredMode = Schema.RequiredMode.REQUIRED)
    private String errorMsg;

    @Schema(description = "父面单号，用于子母件", requiredMode = Schema.RequiredMode.REQUIRED)
    private String parentWaybillCode;
}
