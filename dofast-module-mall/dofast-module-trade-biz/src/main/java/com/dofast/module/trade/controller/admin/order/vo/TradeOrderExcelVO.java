package com.dofast.module.trade.controller.admin.order.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * 交易订单 Excel VO
 *
 * @author 惠智造
 */
@Data
public class TradeOrderExcelVO {

    @ExcelProperty("用户ID")
    private Long id;

    @ExcelProperty("外部渠道订单ID")
    private String channelOrderId;

    @ExcelProperty("订单流水号")
    private String no;

    @ExcelProperty("外部渠道单号")
    private String channelOrderNo;

    @ExcelProperty("订单类型：[0:普通订单 1:秒杀订单 2:拼团订单 3:砍价订单 30:外部订单]")
    private Integer type;

    @ExcelProperty("外部渠道")
    private String channel;

    @ExcelProperty("渠道店铺")
    private Long channelShopId;

    @ExcelProperty("渠道店铺名称")
    private String channelShopName;

    @ExcelProperty("订单来源终端：[1:小程序 2:H5 3:iOS 4:安卓]")
    private Integer terminal;

    @ExcelProperty("用户编号")
    private Long userId;

    @ExcelProperty("用户 IP")
    private String userIp;

    @ExcelProperty("用户备注")
    private String userRemark;

    @ExcelProperty("订单状态：[0:待付款 1:待发货 2:待收货 3:已完成 4:已关闭]")
    private Integer status;

    @ExcelProperty("购买的商品数量")
    private Integer productCount;

    @ExcelProperty("取消类型：[10:超时未支付 20:退款关闭 30:买家取消 40:已通过货到付款交易]")
    private Integer cancelType;

    @ExcelProperty("商家备注")
    private String remark;

    @ExcelProperty("支付订单编号")
    private Long payOrderId;

    @ExcelProperty("是否已支付：[0:未支付 1:已经支付过]")
    private Boolean payed;

    @ExcelProperty("订单支付时间")
    private LocalDateTime payTime;

    @ExcelProperty("支付成功的支付渠道")
    private String payChannelCode;

    @ExcelProperty("订单完成时间")
    private LocalDateTime finishTime;

    @ExcelProperty("订单取消时间")
    private LocalDateTime cancelTime;

    @ExcelProperty("商品原价（总），单位：分")
    private Integer originalPrice;

    @ExcelProperty("订单原价（总），单位：分")
    private Integer orderPrice;

    @ExcelProperty("订单优惠（总），单位：分")
    private Integer discountPrice;

    @ExcelProperty("运费金额，单位：分")
    private Integer deliveryPrice;

    @ExcelProperty("订单调价（总），单位：分")
    private Integer adjustPrice;

    @ExcelProperty("应付金额（总），单位：分")
    private Integer payPrice;

    @ExcelProperty("配置模板的编号")
    private Long deliveryTemplateId;

    @ExcelProperty("发货物流公司编号")
    private String logisticsId;

    @ExcelProperty("物流公司单号")
    private String logisticsNo;

    @ExcelProperty("发货状态")
    private Byte deliveryStatus;

    @ExcelProperty("发货时间")
    private LocalDateTime deliveryTime;

    @ExcelProperty("收货时间")
    private LocalDateTime receiveTime;

    @ExcelProperty("收件人名称")
    private String receiverName;

    @ExcelProperty("收件人手机")
    private String receiverMobile;

    @ExcelProperty("收件人地区编号")
    private Integer receiverAreaId;

    @ExcelProperty("收件人邮编")
    private Integer receiverPostCode;

    @ExcelProperty("收件人详细地址")
    private String receiverDetailAddress;

    @ExcelProperty("售后状态")
    private Integer afterSaleStatus;

    @ExcelProperty("退款金额，单位：分")
    private Integer refundPrice;

    @ExcelProperty("优惠劵编号")
    private Long couponId;

    @ExcelProperty("优惠劵减免金额，单位：分")
    private Integer couponPrice;

    @ExcelProperty("积分抵扣的金额")
    private Integer pointPrice;

    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

    @ExcelProperty("业务员的id")
    private Integer systemUserId;

    @ExcelProperty("业务员名字")
    private String systemUserName;

    @ExcelProperty("销售订单ID")
    private Long mixinOrderId;

    @ExcelProperty("订单号")
    private String saleNo;

    @ExcelProperty("订单备注")
    private String Saleremark;

}
