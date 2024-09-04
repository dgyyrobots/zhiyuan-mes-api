package com.dofast.module.channel.dian3logisticspojo.offline;

import com.dofast.module.channel.dian3logisticspojo.waybill.Dian3WayBillReqPackagesItems;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString(callSuper = true)
@Schema(description = "Dian3 线下订单获取电子面单 包裹信息 Request VO")
public class Dian3OffLineResPackages {
    @Schema(description = "商品信息", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private List<Dian3WayBillReqPackagesItems> items;

    @Schema(description = "渠道类型", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String channelType;

    @Schema(description = "包裹数，表示这个单据要申请几个子母件，用于快运", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Integer totalPackagesCount;

    @Schema(description = "申报价值,单位位元，精确到小数点后1位,子母件单号获取时，保价金额必须都一致",
            requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Double declaredValue;

    @Schema(description = "体积， 单位 ml", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Integer volume;

    @Schema(description = "重量，单位 g", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Integer weight;

    @Schema(description = "包裹ID", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String packageId;

    public Double getDeclaredValue() {
        return Math.round(this.declaredValue * 10) / 10.0; // 保留一位小数，返回处理后的值
    }
}
