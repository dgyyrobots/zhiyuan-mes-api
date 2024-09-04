package com.dofast.module.channel.api.order.dto;

// import com.sun.xml.internal.bind.v2.TODO;
import java.util.List;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class OrderBaseDTO {

    /**
     * 相关单ID
     */
    private String refOid;
    /**
     * 店铺编码
     */
    private String posCode;
    /**
     * 渠道
     *
     * 枚举 {@link TODO order_from_channel 对应的类}
     */
    private String refType;
    /**
     * 应收金额
     */
    private BigDecimal totalFee;
    /**
     * 实付金额
     */
    private BigDecimal payment;
    /**
     * 实收金额
     */
    private BigDecimal receivedPayment;
    /**
     * 原价合计
     */
    private BigDecimal totalPrice;
    /**
     * 售价合计
     */
    private BigDecimal totalSellPrice;
    /**
     * 快递费用
     */
    private BigDecimal postFee;
    /**
     * 服务费
     */
    private BigDecimal serviceFee;
    /**
     * 服务费
     */
    private BigDecimal discountFee;
    /**
     * 下单时间
     */
    private LocalDateTime orderTime;
    /**
     * 修改时间
     */
    private LocalDateTime modifyTime;
    /**
     * 支付时间
     */
    private LocalDateTime payTime;
    /**
     * 发货时间
     */
    private LocalDateTime shippingTime;
    /**
     * 完成时间
     */
    private LocalDateTime finishTime;
    /**
     * 收货人国家
     */
    private String receiverCountry;
    /**
     * 收货人省
     */
    private String receiverState;
    /**
     * 收货人市
     */
    private String receiverCity;
    /**
     * 收件人区/县
     */
    private String receiverDistrict;
    /**
     * 收货人镇
     */
    private String receiverTown;
    /**
     * 收货人ID字段，可用于区分多个订单是否属于同一个收货人
     */
    private String receiverId;
    /**
     * 订单状态
     *
     * 枚举 {@link TODO dian3_order_status 对应的类}
     */
    private String status;
    /**
     * 订单类型
     *
     * 枚举 {@link TODO dian3_order_type 对应的类}
     */
    private String type;
    /**
     * 退款状态
     *
     * 枚举 {@link TODO dian3_refund_status 对应的类}
     */
    private String refundStatus;
    /**
     * 旗帜
     */
    private String flag;
    /**
     * 卖家备注
     */
    private String sellerMemo;
    /**
     * 卖家备注
     */
    private String buyerMemo;
    /**
     * 卖家昵称
     */
    private String openSellerNick;
    /**
     * 买家昵称
     */
    private String openBuyerNick;
    /**
     * 买家昵称
     */
    private String openBuyerId;
    /**
     * 商家物流编码
     */
    private String logisticsPartnerCode;

    /**
     * 物流单号
     */
    private String logisticsOrderNo;
    /**
     * 平台仓库编码
     */
    private String refWhsCode;
    /**
     * 预计发货时间/预约发货时间。一般是指由 买家指定的某个时间。
     */
    private LocalDateTime deliveryTime;
    /**
     * 最晚发货时间。和deliveryTime区分，这个一般是指平台或卖家定义的 承诺 最晚发货时间
     */
    private LocalDateTime latestDeliveryTime;
    /**
     * 订单其他属性
     */
    private Object props;;
    /**
     * 订单标记
     *
     * 枚举 {@link TODO dian3_order_mark 对应的类}
     */
    private List<String> mark2;
    /**
     * 交易类型
     *
     * 枚举 {@link TODO dian3_business_type 对应的类}
     */
    private String businessType;
    /**
     * 关联用户ID
     */
    private Integer userId;

    /**
     * 关联用户ID
     */
    private Integer customerId;
    /**
     * 关联收货地址ID
     */
    private Integer addressId;

    /**
     * 租户ID
     */
    private Integer tenantId;


}