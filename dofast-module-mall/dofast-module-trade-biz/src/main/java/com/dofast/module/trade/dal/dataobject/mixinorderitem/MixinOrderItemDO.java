package com.dofast.module.trade.dal.dataobject.mixinorderitem;

import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.dofast.framework.mybatis.core.dataobject.BaseDO;
import lombok.*;

import java.math.BigDecimal;

/**
 * 销售的物料明细 DO
 *
 * @author 惠智造
 */
@TableName("sales_order_item")
@KeySequence("sales_order_item_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MixinOrderItemDO extends BaseDO {

    /**
     * 销售的物料ID
     */
    @TableId
    private Long id;
    /**
     * 产品物料ID
     */
    private Long itemId;
    /**
     * 产品物料编码
     */
    private String itemCode;
    /**
     * 产品物料名称
     */
    private String itemName;
    /**
     * 销售订单ID
     */
    private Long saleId;
    /**
     * 销售单编码
     */
    private String saleNo;
    /**
     * 标题
     */
    private String title;
    /**
     * 单价
     */
    private BigDecimal singlePrice;
    /**
     * 数量
     */
    private Integer num;
    /**
     * 总价
     */
    private BigDecimal totalPrice;

}
