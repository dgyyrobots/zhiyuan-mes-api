package com.dofast.module.trade.controller.admin.order.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import com.dofast.framework.common.pojo.PageParam;
import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;

import static com.dofast.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 交易订单 Excel 导出 Request VO，参数和 TradeOrderPageReqVO 是一致的")
@Data
public class TradeOrderExportReqVO {

    @Schema(description = "外部渠道订单ID", example = "2313")
    private String channelOrderId;

    @Schema(description = "订单流水号")
    private String no;

    @Schema(description = "外部渠道单号")
    private String channelOrderNo;

    @Schema(description = "订单类型：[0:普通订单 1:秒杀订单 2:拼团订单 3:砍价订单 30:外部订单]", example = "1")
    private Integer type;

    @Schema(description = "外部渠道")
    private String channel;

    @Schema(description = "渠道店铺", example = "22163")
    private Long channelShopId;

    @Schema(description = "渠道店铺名称", example = "李四")
    private String channelShopName;

    @Schema(description = "订单来源终端：[1:小程序 2:H5 3:iOS 4:安卓]")
    private Integer terminal;

    @Schema(description = "用户编号", example = "11199")
    private Long userId;

    @Schema(description = "用户 IP")
    private String userIp;

    @Schema(description = "用户备注", example = "你猜")
    private String userRemark;

    @Schema(description = "订单状态：[0:待付款 1:待发货 2:待收货 3:已完成 4:已关闭]", example = "2")
    private Integer status;

    @Schema(description = "购买的商品数量", example = "16343")
    private Integer productCount;

    @Schema(description = "取消类型：[10:超时未支付 20:退款关闭 30:买家取消 40:已通过货到付款交易]", example = "1")
    private Integer cancelType;

    @Schema(description = "商家备注", example = "随便")
    private String remark;

    @Schema(description = "支付订单编号", example = "23204")
    private Long payOrderId;

    @Schema(description = "是否已支付：[0:未支付 1:已经支付过]")
    private Boolean payed;

    @Schema(description = "订单支付时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] payTime;

    @Schema(description = "支付成功的支付渠道")
    private String payChannelCode;

    @Schema(description = "订单完成时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] finishTime;

    @Schema(description = "订单取消时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] cancelTime;

    @Schema(description = "商品原价（总），单位：分", example = "29628")
    private Integer originalPrice;

    @Schema(description = "订单原价（总），单位：分", example = "4500")
    private Integer orderPrice;

    @Schema(description = "订单优惠（总），单位：分", example = "28618")
    private Integer discountPrice;

    @Schema(description = "运费金额，单位：分", example = "1516")
    private Integer deliveryPrice;

    @Schema(description = "订单调价（总），单位：分", example = "7841")
    private Integer adjustPrice;

    @Schema(description = "应付金额（总），单位：分", example = "11976")
    private Integer payPrice;

    @Schema(description = "配置模板的编号", example = "9086")
    private Long deliveryTemplateId;

    @Schema(description = "发货物流公司编号", example = "10420")
    private Long logisticsId;

    @Schema(description = "物流公司单号")
    private String logisticsNo;

    @Schema(description = "发货状态", example = "2")
    private Byte deliveryStatus;

    @Schema(description = "发货时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] deliveryTime;

    @Schema(description = "收货时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] receiveTime;

    @Schema(description = "收件人名称", example = "赵六")
    private String receiverName;

    @Schema(description = "收件人手机")
    private String receiverMobile;

    @Schema(description = "收件人地区编号", example = "16798")
    private Integer receiverAreaId;

    @Schema(description = "收件人邮编")
    private Integer receiverPostCode;

    @Schema(description = "收件人详细地址")
    private String receiverDetailAddress;

    @Schema(description = "售后状态", example = "1")
    private Integer afterSaleStatus;

    @Schema(description = "退款金额，单位：分", example = "29362")
    private Integer refundPrice;

    @Schema(description = "优惠劵编号", example = "18324")
    private Long couponId;

    @Schema(description = "优惠劵减免金额，单位：分", example = "28751")
    private Integer couponPrice;

    @Schema(description = "积分抵扣的金额", example = "20120")
    private Integer pointPrice;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    @Schema(description = "业务员的id", example = "12223")
    private Integer systemUserId;

    @Schema(description = "业务员名字", example = "赵六")
    private String systemUserName;

}
