package com.dofast.module.channel.controller.admin.order.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.util.List;

@Schema(description = "管理后台 - 店铺订单售后 Response VO")
@Data
@ToString(callSuper = true)
public class ShopAfterSaleRes {
    @Schema(description = "店铺编码", requiredMode = Schema.RequiredMode.NOT_REQUIRED, example = "XQGT_DY_2068")
    private String posCode;

    @Schema(description = "店铺平台类型", requiredMode = Schema.RequiredMode.NOT_REQUIRED, example = "TB")
    private String refPlatform;

    @Schema(description = "子平台类型", requiredMode = Schema.RequiredMode.REQUIRED, example = "TMALL")
    @NotNull
    private String refType;

    @Schema(description = "平台商家昵称", requiredMode = Schema.RequiredMode.NOT_REQUIRED, example = "名名旗舰店")
    private String openSellerNick;

    @Schema(description = "平台买家昵称", requiredMode = Schema.RequiredMode.NOT_REQUIRED, example = "lih500537")
    private String openBuyerNick;

    @Schema(description = "平台售后单号", requiredMode = Schema.RequiredMode.NOT_REQUIRED, example = "7223659294040801593")
    private String refAid;

    @Schema(description = "平台订单号", requiredMode = Schema.RequiredMode.NOT_REQUIRED, example = "5056835697407907322")
    private String refOid;

    @Schema(description = "售后类型", requiredMode = Schema.RequiredMode.NOT_REQUIRED, example = "REFUND")
    private String type;

    @Schema(description = "订单状态,见订单", requiredMode = Schema.RequiredMode.NOT_REQUIRED, example = "COMPLETE")
    private String orderStatus;

    @Schema(description = "退款阶段", requiredMode = Schema.RequiredMode.NOT_REQUIRED, example = "ON_SALE")
    private String refundPhase;

    @Schema(description = "订单总费用", requiredMode = Schema.RequiredMode.NOT_REQUIRED, example = "56")
    private String totalFee;

    @Schema(description = "退款金额", requiredMode = Schema.RequiredMode.NOT_REQUIRED, example = "45")
    private String refundFee;

    @Schema(description = "物流公司", requiredMode = Schema.RequiredMode.NOT_REQUIRED, example = "YT")
    private String logisticsCompany;

    @Schema(description = "物流单号", requiredMode = Schema.RequiredMode.NOT_REQUIRED, example = "YT222")
    private String logisticsOrderNo;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.NOT_REQUIRED, example = "1681822254000")
    private String createTime;

    @Schema(description = "更新时间", requiredMode = Schema.RequiredMode.NOT_REQUIRED, example = "1681889334000")
    private String updateTime;

    @Schema(description = "平台退款申请时间", requiredMode = Schema.RequiredMode.NOT_REQUIRED, example = "1681822210000")
    private String refundCreateTime;

    @Schema(description = "平台退款更新时间", requiredMode = Schema.RequiredMode.NOT_REQUIRED, example = "1681889220000")
    private String refundUpdateTime;

    @Schema(description = "售后状态", requiredMode = Schema.RequiredMode.NOT_REQUIRED, example = "WAIT_SELLER_AGREE")
    private String status;

    @Schema(description = "退款原因", requiredMode = Schema.RequiredMode.NOT_REQUIRED, example = "多拍/错拍/不想要")
    private String reason;

    @Schema(description = "退款说明", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String desc;

    @Schema(description = "订单详情", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private List<ShopAfterSaleItemRes> lines;
}
