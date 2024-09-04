package com.dofast.module.channel.dal.dataobject.ordergoods;

import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.dofast.framework.mybatis.core.dataobject.BaseDO;
import java.math.BigDecimal;
import lombok.*;

/**
 * 子订单 DO
 *
 * @author 惠智造
 */
@TableName("channel_order_goods")
@KeySequence("channel_order_goods_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderGoodsDO extends BaseDO {

    /**
     * 订单商品
     */
    @TableId
    private Integer id;
    /**
     * 关联的id
     */
    private String refOid;
    /**
     * 子订单id
     */
    private String refOlId;
    /**
     * 平台SPU_ID
     */
    private String refSpuId;
    /**
     * 平台SKU_ID
     */
    private String refSkuId;
    /**
     * 商家编码
     */
    private String outerId;
    /**
     * 货品标题
     */
    private String title;
    /**
     * 货品规格
     */
    private String standards;
    /**
     * 售价合计
     */
    private BigDecimal totalSellPrice;
    /**
     * 原价合计
     */
    private BigDecimal totalPrice;
    /**
     * 原价
     */
    private BigDecimal price;
    /**
     * 售价
     */
    private BigDecimal sellPrice;
    /**
     * 应收金额
     */
    private BigDecimal totalFee;
    /**
     * 单个商品应收金额
     */
    private BigDecimal singleFee;
    /**
     * 数量
     */
    private Integer num;
    /**
     * 子订单售后状态
     *
     * 枚举 {@link TODO dian3_sub_refund_status 对应的类}
     */
    private String refundStatus;
    /**
     * 子订单状态
     *
     * 枚举 {@link TODO dian3_order_type 对应的类}
     */
    private String status;
    /**
     * 商品图片URL
     */
    private String picUrl;
    /**
     * 是否赠品
     *
     * 枚举 {@link TODO infra_boolean_string 对应的类}
     */
    private Integer isFreeGift;
    /**
     * 关联用户ID
     */
    private Integer userId;

    /**
     * 关联交易用户ID
     */
    private Integer customerId;


    private Integer tenantId;

}
