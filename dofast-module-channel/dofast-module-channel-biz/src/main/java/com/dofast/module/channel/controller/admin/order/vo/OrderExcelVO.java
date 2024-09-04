package com.dofast.module.channel.controller.admin.order.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.util.List;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.alibaba.excel.annotation.ExcelProperty;
import com.dofast.framework.excel.core.annotations.DictFormat;
import com.dofast.framework.excel.core.convert.DictConvert;


/**
 * 主订单 Excel VO
 *
 * @author 惠智造
 */
@Data
public class OrderExcelVO {

    @ExcelProperty("订单ID")
    private Integer id;

    @ExcelProperty("相关单ID")
    private String refOid;

    @ExcelProperty("店铺编码")
    private String posCode;

    @ExcelProperty(value = "渠道", converter = DictConvert.class)
    @DictFormat("order_from_channel") // TODO 代码优化：建议设置到对应的 XXXDictTypeConstants 枚举类中
    private String refType;

    @ExcelProperty("应收金额")
    private BigDecimal totalFee;

    @ExcelProperty("实付金额")
    private BigDecimal payment;

    @ExcelProperty("实收金额")
    private BigDecimal receivedPayment;

    @ExcelProperty("原价合计")
    private BigDecimal totalPrice;

    @ExcelProperty("售价合计")
    private BigDecimal totalSellPrice;

    @ExcelProperty("快递费用")
    private BigDecimal postFee;

    @ExcelProperty("服务费")
    private BigDecimal serviceFee;

    @ExcelProperty("服务费")
    private BigDecimal discountFee;

    @ExcelProperty("下单时间")
    private LocalDateTime orderTime;

    @ExcelProperty("修改时间")
    private LocalDateTime modifyTime;

    @ExcelProperty("支付时间")
    private LocalDateTime payTime;

    @ExcelProperty("发货时间")
    private LocalDateTime shippingTime;

    @ExcelProperty("完成时间")
    private LocalDateTime finishTime;

    @ExcelProperty("收货人国家")
    private String receiverCountry;

    @ExcelProperty("收货人省")
    private String receiverState;

    @ExcelProperty("收货人市")
    private String receiverCity;

    @ExcelProperty("收件人区/县")
    private String receiverDistrict;

    @ExcelProperty("收货人镇")
    private String receiverTown;

    @ExcelProperty("收货人ID字段，可用于区分多个订单是否属于同一个收货人")
    private String receiverId;

    @ExcelProperty(value = "订单状态", converter = DictConvert.class)
    @DictFormat("dian3_order_status") // TODO 代码优化：建议设置到对应的 XXXDictTypeConstants 枚举类中
    private String status;

    @ExcelProperty(value = "订单类型", converter = DictConvert.class)
    @DictFormat("dian3_order_type") // TODO 代码优化：建议设置到对应的 XXXDictTypeConstants 枚举类中
    private String type;

    @ExcelProperty(value = "退款状态", converter = DictConvert.class)
    @DictFormat("dian3_refund_status") // TODO 代码优化：建议设置到对应的 XXXDictTypeConstants 枚举类中
    private String refundStatus;

    @ExcelProperty("旗帜")
    private String flag;

    @ExcelProperty("卖家备注")
    private String sellerMemo;

    @ExcelProperty("卖家备注")
    private String buyerMemo;

    @ExcelProperty("卖家昵称")
    private String openSellerNick;

    @ExcelProperty("买家昵称")
    private String openBuyerNick;

    @ExcelProperty("买家昵称")
    private String openBuyerId;

    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

    @ExcelProperty("商家物流编码")
    private String logisticsPartnerCode;

    @ExcelProperty("物流单号")
    private String logisticsOrderNo;

    @ExcelProperty("平台仓库编码")
    private String refWhsCode;

    @ExcelProperty("预计发货时间/预约发货时间。一般是指由 买家指定的某个时间。")
    private LocalDateTime deliveryTime;

    @ExcelProperty("最晚发货时间。和deliveryTime区分，这个一般是指平台或卖家定义的 承诺 最晚发货时间")
    private LocalDateTime latestDeliveryTime;

    @ExcelProperty("订单其他属性")
    private Object props;

    @ExcelProperty(value = "订单标记", converter = DictConvert.class)
    @DictFormat("dian3_order_mark") // TODO 代码优化：建议设置到对应的 XXXDictTypeConstants 枚举类中
    private List<String> mark2;

    @ExcelProperty(value = "交易类型", converter = DictConvert.class)
    @DictFormat("dian3_business_type") // TODO 代码优化：建议设置到对应的 XXXDictTypeConstants 枚举类中
    private String businessType;

    @ExcelProperty("关联用户ID")
    private Integer userId;

    @ExcelProperty("关联收货地址ID")
    private Integer addressId;

    @ExcelProperty("是否转化为商城订单")
    private Boolean isTranslate;
}
