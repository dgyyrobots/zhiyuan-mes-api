package com.dofast.module.channel.dian3logisticspojo.offline;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static com.dofast.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "Dian3 线下订单获取电子面单 订单信息 Response VO")
@Data
@ToString(callSuper = true)
public class Dian3OffLineResOrderInfo {
    @Schema(description = "订单编号", example = "200", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull
    private String refOid;

    @Schema(description = "店铺编码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull
    private String posCode;

    @Schema(description = "支付单号", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String payNo;

    @Schema(description = "实付金额", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull
    private String payment;

    @Schema(description = "实收金额", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String receivedPayment;

    @Schema(description = "快递费用", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String postFee;

    @Schema(description = "服务费", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String serviceFee;

    @Schema(description = "收货人姓名", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull
    private String receiverName;

    @Schema(description = "收货人国家", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String receiverCountry;

    @Schema(description = "收货人省", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull
    private String receiverState;

    @Schema(description = "收货人市", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull
    private String receiverCity;

    @Schema(description = "收件人区/县", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull
    private String receiverDistrict;

    @Schema(description = "收货人镇", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull
    private String receiverTown;

    @Schema(description = "收货详细地址", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull
    private String receiverAddress;

    @Schema(description = "收货人手机号", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull
    private String receiverMobile;

    @Schema(description = "收货人固话", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull
    private String receiverPhone;

    @Schema(description = "收货地邮编", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull
    private String receiverZip;

    @Schema(description = "下单时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull
    private String orderTime;

    @Schema(description = "修改时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull
    private String modifyTime;

    @Schema(description = "买家昵称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull
    private String openBuyerNick;

    @Schema(description = "买家昵称", example = "23598", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull
    private String openBuyerId;

    @Schema(description = "支付时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull
    private String  payTime;

    @Schema(description = "发货时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull
    private String shippingTime;

    @Schema(description = "订单状态", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull
    private String status;

    @Schema(description = "退款状态", example = "2", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull
    private String refundStatus;

    @Schema(description = "旗帜", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull
    private String flag;

    @Schema(description = "卖家备注", example = "随便", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull
    private String sellerMemo;

    @Schema(description = "卖家备注", example = "你猜", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull
    private String buyerMemo;

    @Schema(description = "订单商品列表", example = "你猜", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull
    private List<Dian3OffLineResOrderItem> lines;
}
