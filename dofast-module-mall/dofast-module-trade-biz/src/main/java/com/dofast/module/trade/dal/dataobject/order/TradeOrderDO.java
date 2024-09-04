package com.dofast.module.trade.dal.dataobject.order;

import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.dofast.framework.mybatis.core.dataobject.BaseDO;

import com.dofast.framework.common.enums.TerminalEnum;
import com.dofast.framework.mybatis.core.dataobject.BaseDO;
import com.dofast.module.member.api.user.dto.MemberUserRespDTO;
import com.dofast.module.trade.dal.dataobject.brokerage.BrokerageUserDO;
import com.dofast.module.trade.dal.dataobject.delivery.DeliveryExpressDO;
import com.dofast.module.trade.dal.dataobject.delivery.DeliveryPickUpStoreDO;
import com.dofast.module.trade.enums.delivery.DeliveryTypeEnum;
import com.dofast.module.trade.enums.order.TradeOrderCancelTypeEnum;
import com.dofast.module.trade.enums.order.TradeOrderRefundStatusEnum;
import com.dofast.module.trade.enums.order.TradeOrderStatusEnum;
import com.dofast.module.trade.enums.order.TradeOrderTypeEnum;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableName;


import lombok.*;

import java.time.LocalDateTime;

/**
 * 交易订单 DO
 *
 * @author 惠智造
 * @author 芋道源码
 */
@TableName("trade_order")
@KeySequence("trade_order_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TradeOrderDO extends BaseDO {

    /**
     * 发货物流公司编号 - 空（无需发货）
     */
    public static final Long LOGISTICS_ID_NULL = 0L;

    // === 订单基本信息 ===
    /**
     * 订单编号，主键自增
     */
    private Long id;
    /**
     * 订单流水号
     *
     * 例如说，1146347329394184195
     */
    private String no;
    /**
     * 订单类型
     *
     * 枚举 {@link TradeOrderTypeEnum}
     */
    private Integer type;
    /**
     * 订单来源
     *
     * 枚举 {@link TerminalEnum}
     */
    private Integer terminal;
    /**
     * 用户编号
     *
     * 关联 MemberUserDO 的 id 编号
     */
    private Long userId;
    /**
     * 用户 IP
     */
    private String userIp;
    /**
     * 用户备注
     */
    private String userRemark;
    /**
     * 订单状态
     *
     * 枚举 {@link TradeOrderStatusEnum}
     */
    private Integer status;
    /**
     * 购买的商品数量
     */
    private Integer productCount;
    /**


     * 取消类型：[10:超时未支付 20:退款关闭 30:买家取消 40:已通过货到付款交易]
     */
    private Integer cancelType;
    /**
     * 商家备注
     */
    private String remark;
    /**
         * 支付订单编号
     */
    private Long payOrderId;
    /**
     * 是否已支付：[0:未支付 1:已经支付过]
     */
    private Boolean payed;
    /**
     * 订单支付时间
     */
    private LocalDateTime payTime;
    /**
     * 支付成功的支付渠道
     */
    private String payChannelCode;
    /**

     * 订单完成时间
     */
    private LocalDateTime finishTime;
    /**
     * 订单取消时间
     */
    private LocalDateTime cancelTime;

    /**
     * 银行卡ID
     */
    private Long depositor;

    /**
     * 是否评价
     *
     * 0 - 已评价
     * 1 - 未评价
     */
    private Boolean commentStatus;

    /**
     * 推广人编号
     *
     * 关联 {@link BrokerageUserDO#getId()} 字段，即 {@link MemberUserRespDTO#getId()} 字段
     */
    private Long brokerageUserId;

    // === 价格 + 支付基本信息 ===

    // 价格文档 - 淘宝：https://open.taobao.com/docV3.htm?docId=108471&docType=1
    // 价格文档 - 京东到家：https://openo2o.jddj.com/api/getApiDetail/182/4d1494c5e7ac4679bfdaaed950c5bc7f.htm
    // 价格文档 - 有赞：https://doc.youzanyun.com/detail/API/0/906

    /**
     * 是否已支付
     *
     * true - 已经支付过
     * false - 没有支付过
     */
    private Boolean payStatus;

    /**
     * 商品原价，单位：分
     *
     * totalPrice = {@link TradeOrderItemDO#getOriginalPrice()} ()} * {@link TradeOrderItemDO#getCount()} 求和
     *
     * 对应 taobao 的 trade.total_fee 字段
     */
    private Integer totalPrice;
    /**
     * 优惠金额，单位：分
     *
     * 对应 taobao 的 order.discount_fee 字段
     */
    private Integer discountPrice;
    /**
     * 运费金额，单位：分
     */
    private Integer deliveryPrice;
    /**
     * 订单调价，单位：分
     *
     * 正数，加价；负数，减价
     */
    private Integer adjustPrice;
    /**
     * 应付金额（总），单位：分
     *
     * = {@link #totalPrice}
     * - {@link #couponPrice}
     * - {@link #pointPrice}
     * - {@link #discountPrice}
     * + {@link #deliveryPrice}
     * + {@link #adjustPrice}
     * - {@link #vipPrice}
     */
    private Integer payPrice;

    // === 收件 + 物流基本信息 ===
    /**
     * 配送方式
     *
     * 枚举 {@link DeliveryTypeEnum}
     */
    private Integer deliveryType;
    /**
     * 发货物流公司编号
     *
     * 如果无需发货，则 logisticsId 设置为 0。原因是，不想再添加额外字段
     *
     * 关联 {@link DeliveryExpressDO#getId()}
     */
    private String logisticsId;
    /**
     * 发货物流单号
     *
     * 如果无需发货，则 logisticsNo 设置 ""。原因是，不想再添加额外字段
     */
    private String logisticsNo;
    /**
     * 发货时间
     */
    private LocalDateTime deliveryTime;

    /**
     * 收货时间
     */
    private LocalDateTime receiveTime;
    /**
     * 收件人名称
     */
    private String receiverName;
    /**
     * 收件人手机
     */
    private String receiverMobile;
    /**
     * 收件人地区编号
     */
    private Integer receiverAreaId;
    /**
     * 收件人详细地址
     */
    private String receiverDetailAddress;

    /**
     * 自提门店编号
     *
     * 关联 {@link DeliveryPickUpStoreDO#getId()}
     */
    private Long pickUpStoreId;
    /**
     * 自提核销码
     */
    private String pickUpVerifyCode;

    // === 售后基本信息 ===
    /**
     * 售后状态
     *
     * 枚举 {@link TradeOrderRefundStatusEnum}
     */
    private Integer refundStatus;
    /**
     * 退款金额，单位：分
     *
     * 注意，退款并不会影响 {@link #payPrice} 实际支付金额
     * 也就说，一个订单最终产生多少金额的收入 = payPrice - refundPrice
     */
    private Integer refundPrice;

    // === 营销基本信息 ===
    /**
     * 优惠劵编号
     */
    private Long couponId;
    /**
     * 优惠劵减免金额，单位：分
     *
     * 对应 taobao 的 trade.coupon_fee 字段
     */
    private Integer couponPrice;
    /**
     * 使用的积分
     */
    private Integer usePoint;
    /**
     * 积分抵扣的金额，单位：分
     *
     * 对应 taobao 的 trade.point_fee 字段
     */
    private Integer pointPrice;
    /**
     * 赠送的积分
     */
    private Integer givePoint;
    /**
     * 退还的使用的积分
     */
    private Integer refundPoint;
    /**
     * VIP 减免金额，单位：分
     */
    private Integer vipPrice;

    /**
     * 秒杀活动编号
     *
     * 关联 SeckillActivityDO 的 id 字段
     */
    private Long seckillActivityId;

    /**
     * 砍价活动编号
     *
     * 关联 BargainActivityDO 的 id 字段
     */
    private Long bargainActivityId;
    /**
     * 砍价记录编号
     *
     * 关联 BargainRecordDO 的 id 字段
     */
    private Long bargainRecordId;

    /**
     * 拼团活动编号
     *
     * 关联 CombinationActivityDO 的 id 字段
     */
    private Long combinationActivityId;
    /**
     * 拼团团长编号
     *
     * 关联 CombinationRecordDO 的 headId 字段
     */
    private Long combinationHeadId;
    /**
     * 拼团记录编号
     *
     * 关联 CombinationRecordDO 的 id 字段
     */
    private Long combinationRecordId;



    /**
     * 外部渠道订单ID
     */
    private String channelOrderId;
    /**
     * 外部渠道单号
     */
    private String channelOrderNo;
    /**
     * 外部渠道
     */
    private String channel;
    /**
     * 渠道店铺
     */
    private Long channelShopId;
    /**
     * 渠道店铺名称
     */
    private String channelShopName;
    /**
     * 商品原价（总），单位：分
     */
    private Integer originalPrice;
    /**
     * 订单原价（总），单位：分
     */
    private Integer orderPrice;
    /**
     * 配置模板的编号
     */
    private Long deliveryTemplateId;
    /**
     * 发货状态
     */
    private Byte deliveryStatus;
    /**
     * 收件人邮编
     */
    private Integer receiverPostCode;
    /**
     * 售后状态
     */
    private Integer afterSaleStatus;
    /**
     * 业务员的id
     */
    private Integer systemUserId;
    /**
     * 业务员名字
     */
    private String systemUserName;
    /**
     * 销售订单ID
     */
    private Long mixinOrderId;
    /**
     * 发货人
     */
    private Integer deliveryUser;
}
