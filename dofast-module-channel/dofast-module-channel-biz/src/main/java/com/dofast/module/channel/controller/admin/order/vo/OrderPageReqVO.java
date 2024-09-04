package com.dofast.module.channel.controller.admin.order.vo;

import com.dofast.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import static com.dofast.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 主订单分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class OrderPageReqVO extends PageParam {

    @Schema(description = "相关单ID", example = "200")
    private String refOid;

    @Schema(description = "店铺编码")
    private String posCode;

    @Schema(description = "渠道", example = "1")
    private String refType;

    @Schema(description = "应收金额")
    private BigDecimal totalFee;

    @Schema(description = "实付金额")
    private BigDecimal payment;

    @Schema(description = "实收金额")
    private BigDecimal receivedPayment;

    @Schema(description = "原价合计", example = "22520")
    private BigDecimal totalPrice;

    @Schema(description = "售价合计", example = "10845")
    private BigDecimal totalSellPrice;

    @Schema(description = "快递费用")
    private BigDecimal postFee;

    @Schema(description = "服务费")
    private BigDecimal serviceFee;

    @Schema(description = "服务费")
    private BigDecimal discountFee;

    @Schema(description = "下单时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] orderTime;

    @Schema(description = "修改时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] modifyTime;

    @Schema(description = "支付时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] payTime;

    @Schema(description = "发货时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] shippingTime;

    @Schema(description = "完成时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] finishTime;

    @Schema(description = "收货人国家")
    private String receiverCountry;

    @Schema(description = "收货人省")
    private String receiverState;

    @Schema(description = "收货人市")
    private String receiverCity;

    @Schema(description = "收件人区/县")
    private String receiverDistrict;

    @Schema(description = "收货人镇")
    private String receiverTown;

    @Schema(description = "收货人ID字段，可用于区分多个订单是否属于同一个收货人", example = "14265")
    private String receiverId;

    @Schema(description = "订单状态", example = "1")
    private String status;

    @Schema(description = "订单类型", example = "1")
    private String type;

    @Schema(description = "退款状态", example = "2")
    private String refundStatus;

    @Schema(description = "旗帜")
    private String flag;

    @Schema(description = "卖家备注", example = "随便")
    private String sellerMemo;

    @Schema(description = "卖家备注", example = "你猜")
    private String buyerMemo;

    @Schema(description = "卖家昵称")
    private String openSellerNick;

    @Schema(description = "买家昵称")
    private String openBuyerNick;

    @Schema(description = "买家昵称", example = "23598")
    private String openBuyerId;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    @Schema(description = "商家物流编码")
    private String logisticsPartnerCode;

    @Schema(description = "物流单号")
    private String logisticsOrderNo;

    @Schema(description = "平台仓库编码")
    private String refWhsCode;

    @Schema(description = "预计发货时间/预约发货时间。一般是指由 买家指定的某个时间。")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] deliveryTime;

    @Schema(description = "最晚发货时间。和deliveryTime区分，这个一般是指平台或卖家定义的 承诺 最晚发货时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] latestDeliveryTime;

    @Schema(description = "订单其他属性")
    private Object props;

    @Schema(description = "订单标记")
    private List<String> mark2;

    @Schema(description = "交易类型", example = "2")
    private String businessType;

    @Schema(description = "关联用户ID", example = "16307")
    private Integer userId;

    @Schema(description = "关联收货地址ID", example = "2783")
    private Integer addressId;

    @Schema(description = "关联交易客户ID", example = "16979")
    private Integer customerId;

    @Schema(description = "关联交易客户ID", example = "16979")
    private Boolean isTranslate;
}
