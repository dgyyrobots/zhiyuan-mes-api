package com.dofast.module.channel.dian3logisticspojo.offline;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Schema(description = "Dian3 线下订单获取电子面单 订单商品列表 Response VO")
@Data
@ToString(callSuper = true)
public class Dian3OffLineResOrderItem {
    @Schema(description = "平台SPU_ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "3608142077566821990")
    @NotNull(message = "平台SPU_ID不能为空")
    private String refSpuId;

    @Schema(description = "平台SKU_ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1761788121087032")
    @NotNull(message = "平台SKU_ID不能为空")
    private String refSkuId;

    @Schema(description = "售价", requiredMode = Schema.RequiredMode.REQUIRED, example = "5426")
    @NotNull(message = "售价不能为空")
    private String sellPrice;

    @Schema(description = "售价合计", requiredMode = Schema.RequiredMode.REQUIRED, example = "19793")
    @NotNull(message = "售价合计不能为空")
    private String totalSellPrice;

    @Schema(description = "原价合计", requiredMode = Schema.RequiredMode.REQUIRED, example = "14820")
    @NotNull(message = "原价合计不能为空")
    private String totalPrice;

    @Schema(description = "原价", requiredMode = Schema.RequiredMode.REQUIRED, example = "16865")
    @NotNull(message = "原价不能为空")
    private String price;

    @Schema(description = "数量", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "数量不能为空")
    private String num;

    @Schema(description = "货品标题", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "货品标题不能为空")
    private String title;

    @Schema(description = "商家编码", requiredMode = Schema.RequiredMode.REQUIRED, example = "29640")
    @NotNull(message = "商家编码不能为空")
    private String outerId;

    @Schema(description = "货品规格", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String standards;

    @Schema(description = "子订单id", requiredMode = Schema.RequiredMode.REQUIRED, example = "11867")
    @NotNull(message = "子订单id不能为空")
    private String refOlId;

    @Schema(description = "子订单售后状态", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "子订单售后状态不能为空")
    private String refundStatus;

    @Schema(description = "子订单状态", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @NotNull(message = "子订单状态不能为空")
    private String status;

    @Schema(description = "商品优惠金额", requiredMode = Schema.RequiredMode.NOT_REQUIRED, example = "2")
    private String discountFee;
}
