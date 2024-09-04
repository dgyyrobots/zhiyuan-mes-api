package com.dofast.module.channel.dian3logisticspojo.waybill;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@ToString(callSuper = true)
@Schema(description = "Dian3 获取电子面单 包裹信息 Request VO")
public class Dian3WayBillReqPackages {
    @Schema(description = "外部单据编号", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull
    private String outerCode;

    @Schema(description = "店铺编号", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull
    private String posCode;

    @Schema(description = "平台订单编号", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull
    private String refOid;

    @Schema(description = "商品信息", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private List<Dian3WayBillReqPackagesItems> items;

    @Schema(description = "收件人信息", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Dian3WayBillReqPackagesReceipt receipt;

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
