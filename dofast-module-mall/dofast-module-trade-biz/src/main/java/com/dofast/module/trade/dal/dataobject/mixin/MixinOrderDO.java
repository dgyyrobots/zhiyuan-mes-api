package com.dofast.module.trade.dal.dataobject.mixin;

import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.dofast.framework.mybatis.core.dataobject.BaseDO;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 销售订单 DO
 *
 * @author 惠智造
 */
@TableName("sales_order")
@KeySequence("sales_order_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MixinOrderDO extends BaseDO {

    /**
     * 销售订单ID
     */
    @TableId
    private Long id;

    /**
     * 流程实例的编号
     */
    private String processInstanceId;

    /**
     * 销售单编码
     */
    private String saleNo;
    /**
     * 标题
     */
    private String title;
    /**
     * 标题
     */
    private String address;
    /**
     * 业务员
     */
    private Integer saler;
    /**
     * 业务员名称
     */
    private String salerName;
    /**
     * 结算方式
     */
    private Integer settlementMethod;
    /**
     * 预计交付时间
     */
    private LocalDateTime estimatedDeliveryTime;
    /**
     * 订单总金额
     */
    private BigDecimal price;
    /**
     * 相关商品
     */
    private String goodsList;
    /**
     * 相关图片
     */
    private String pics;
    /**
     * 是否打印
     */
    private String isPrint;
    /**
     * 订单状态
     */
    private String status;

}
