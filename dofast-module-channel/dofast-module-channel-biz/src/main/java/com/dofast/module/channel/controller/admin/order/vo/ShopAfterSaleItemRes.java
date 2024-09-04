package com.dofast.module.channel.controller.admin.order.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Data
@Schema(description = "管理后台 - 店铺订单售后子订单 Response VO")
@ToString(callSuper = true)
public class ShopAfterSaleItemRes {
    @Schema(description = "平台售后单id", requiredMode = Schema.RequiredMode.NOT_REQUIRED, example = "7223659294040801593")
    private String refAid;

    @Schema(description = "平台售后子单id", requiredMode = Schema.RequiredMode.NOT_REQUIRED, example = "72236592940408015935056835697407907322")
    private String refAlId;

    @Schema(description = "平台订单id", requiredMode = Schema.RequiredMode.NOT_REQUIRED, example = "5056835697407907322")
    private String refOid;

    @Schema(description = "平台子订单id", requiredMode = Schema.RequiredMode.NOT_REQUIRED, example = "5056835697407907322")
    private String refOlId;

    @Schema(description = "总收入", requiredMode = Schema.RequiredMode.REQUIRED, example = "3")
    @NotNull
    private String totalFee;

    @Schema(description = "退款金额", requiredMode = Schema.RequiredMode.REQUIRED, example = "3")
    @NotNull
    private String refundFee;

    @Schema(description = "单价", requiredMode = Schema.RequiredMode.REQUIRED, example = "3")
    @NotNull
    private String price;

    @Schema(description = "商品数量", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull
    private String num;

    @Schema(description = "平台SPU_ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "3608142077566821990")
    @NotNull
    private String refSpuId;

    @Schema(description = "平台SKU_ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1761788121087032")
    @NotNull
    private String refSkuId;

    @Schema(description = "商家编码", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String outerId;

    @Schema(description = "商品标题", requiredMode = Schema.RequiredMode.REQUIRED, example = "沉香DIY双圈手串")
    @NotNull
    private String title;

    @Schema(description = "购买的sku(换出的sku)只有换货单有值；表示订单原有商品", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String refBoughtSkuId;

    @Schema(description = "商品退款状态", requiredMode = Schema.RequiredMode.NOT_REQUIRED, example = "WAIT_SELLER_AGREE")
    private String refundStatus;
}
