package com.dofast.module.channel.dian3logisticspojo.offline;

import com.dofast.module.channel.dian3logisticspojo.waybill.Dian3WayBillReqPackagesReceipt;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@ToString(callSuper = true)
@Schema(description = "Dian3 线下订单获取电子面单号 Response VO")
public class Dian3OffLineRes {
    @Schema(description = "OMS商家物流编码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull
    private String cpCode;

    @Schema(description = "打印模板", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String templateUrl;

    @Schema(description = "发件人信息", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Dian3WayBillReqPackagesReceipt send;

    @Schema(description = "订单包裹信息列表", example = "你猜", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull
    private List<Dian3OffLineResOrder> orders;
}
