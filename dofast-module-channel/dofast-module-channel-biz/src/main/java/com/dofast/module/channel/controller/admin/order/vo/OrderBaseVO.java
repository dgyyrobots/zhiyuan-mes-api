package com.dofast.module.channel.controller.admin.order.vo;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import javax.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import static com.dofast.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

/**
 * 主订单 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class OrderBaseVO implements Serializable {

    @Schema(description = "相关单ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "200")
    @NotNull(message = "相关单ID不能为空")
    private String refOid;

    @Schema(description = "店铺编码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "店铺编码不能为空")
    private String posCode;

    @Schema(description = "渠道", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "渠道不能为空")
    private String refType;

    @Schema(description = "应收金额", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "应收金额不能为空")
    private BigDecimal totalFee;

    @Schema(description = "实付金额", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "实付金额不能为空")
    private BigDecimal payment;

    @Schema(description = "实收金额", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "实收金额不能为空")
    private BigDecimal receivedPayment;

    @Schema(description = "原价合计", requiredMode = Schema.RequiredMode.REQUIRED, example = "22520")
    @NotNull(message = "原价合计不能为空")
    private BigDecimal totalPrice;

    @Schema(description = "售价合计", requiredMode = Schema.RequiredMode.REQUIRED, example = "10845")
    @NotNull(message = "售价合计不能为空")
    private BigDecimal totalSellPrice;

    @Schema(description = "快递费用", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "快递费用不能为空")
    private BigDecimal postFee;

    @Schema(description = "服务费", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "服务费不能为空")
    private BigDecimal serviceFee;

    @Schema(description = "服务费", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "服务费不能为空")
    private BigDecimal discountFee;

    @Schema(description = "下单时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "下单时间不能为空")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime orderTime;

    @Schema(description = "修改时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "修改时间不能为空")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime modifyTime;

    @Schema(description = "支付时间", requiredMode = Schema.RequiredMode.REQUIRED)
//    @NotNull(message = "支付时间不能为空")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime payTime;

    @Schema(description = "发货时间", requiredMode = Schema.RequiredMode.REQUIRED)
//    @NotNull(message = "发货时间不能为空")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime shippingTime;

    @Schema(description = "完成时间", requiredMode = Schema.RequiredMode.REQUIRED)
//    @NotNull(message = "完成时间不能为空")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime finishTime;

    @Schema(description = "收货人国家", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "收货人国家不能为空")
    private String receiverCountry;

    @Schema(description = "收货人省", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "收货人省不能为空")
    private String receiverState;

    @Schema(description = "收货人市", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "收货人市不能为空")
    private String receiverCity;

    @Schema(description = "收件人区/县", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "收件人区/县不能为空")
    private String receiverDistrict;

    @Schema(description = "收货人镇", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "收货人镇不能为空")
    private String receiverTown;

    @Schema(description = "收货人ID字段，可用于区分多个订单是否属于同一个收货人", requiredMode = Schema.RequiredMode.REQUIRED, example = "14265")
    @NotNull(message = "收货人ID字段，可用于区分多个订单是否属于同一个收货人不能为空")
    private String receiverId;

    @Schema(description = "订单状态", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "订单状态不能为空")
    private String status;

    @Schema(description = "订单类型", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "订单类型不能为空")
    private String type;

    @Schema(description = "退款状态", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @NotNull(message = "退款状态不能为空")
    private String refundStatus;

    @Schema(description = "旗帜", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "旗帜不能为空")
    private String flag;

    @Schema(description = "卖家备注", requiredMode = Schema.RequiredMode.REQUIRED, example = "随便")
    @NotNull(message = "卖家备注不能为空")
    private String sellerMemo;

    @Schema(description = "卖家备注", requiredMode = Schema.RequiredMode.REQUIRED, example = "你猜")
    @NotNull(message = "卖家备注不能为空")
    private String buyerMemo;

    @Schema(description = "卖家昵称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "卖家昵称不能为空")
    private String openSellerNick;

    @Schema(description = "买家昵称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "买家昵称不能为空")
    private String openBuyerNick;

    @Schema(description = "买家昵称", requiredMode = Schema.RequiredMode.REQUIRED, example = "23598")
    @NotNull(message = "买家昵称不能为空")
    private String openBuyerId;

    @Schema(description = "商家物流编码", requiredMode = Schema.RequiredMode.REQUIRED)
//    @NotNull(message = "商家物流编码不能为空")
    private String logisticsPartnerCode;

    @Schema(description = "物流单号", requiredMode = Schema.RequiredMode.REQUIRED)
//    @NotNull(message = "物流单号不能为空")
    private String logisticsOrderNo;

    @Schema(description = "平台仓库编码", requiredMode = Schema.RequiredMode.REQUIRED)
//    @NotNull(message = "平台仓库编码不能为空")
    private String refWhsCode;

    @Schema(description = "预计发货时间/预约发货时间。一般是指由 买家指定的某个时间。", requiredMode = Schema.RequiredMode.REQUIRED)
//    @NotNull(message = "预计发货时间/预约发货时间。一般是指由 买家指定的某个时间。不能为空")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime deliveryTime;

    @Schema(description = "最晚发货时间。和deliveryTime区分，这个一般是指平台或卖家定义的 承诺 最晚发货时间", requiredMode = Schema.RequiredMode.REQUIRED)
//    @NotNull(message = "最晚发货时间。和deliveryTime区分，这个一般是指平台或卖家定义的 承诺 最晚发货时间不能为空")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime latestDeliveryTime;

    @Schema(description = "订单其他属性", requiredMode = Schema.RequiredMode.REQUIRED)
//    @NotNull(message = "订单其他属性不能为空")
    private Object props;

    @Schema(description = "订单标记", requiredMode = Schema.RequiredMode.REQUIRED)
//    @NotNull(message = "订单标记不能为空")
    private List<String> mark2;

    @Schema(description = "交易类型", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @NotNull(message = "交易类型不能为空")
    private String businessType;

    @Schema(description = "关联收货地址ID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, example = "26690")
    private Integer addressId;

    @Schema(description = "关联用户ID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, example = "16979")
    private Integer userId;

    @Schema(description = "关联交易客户ID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, example = "16979")
    private Integer customerId;

    @Schema(description = "租户ID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, example = "16979")
    private Integer tenantId;

    @Schema(description = "是否转化为商城订单", requiredMode = Schema.RequiredMode.NOT_REQUIRED, example = "16979")
    private Boolean isTranslate;

//    @Schema(description = "关联的商城订单的id", requiredMode = Schema.RequiredMode.NOT_REQUIRED, example = "16979")
//    private long tradeOrderId;
}

class Props{

}