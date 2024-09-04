package com.dofast.module.purchase.dal.dataobject.goods;

import java.time.LocalDate;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import com.dofast.framework.mybatis.core.dataobject.BaseDO;

/**
 * 采购商品明细 DO
 *
 * @author 惠智造
 */
@TableName("purchase_goods")
@KeySequence("purchase_goods_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GoodsDO extends BaseDO {

    /**
     * id
     */
    @TableId
    private Integer id;
    /**
     * 采购订单Id
     */
    private Integer purchaseId;
    /**
     * 商品编号
     */
    private String goodsNumber;
    /**
     * 商品名称
     */
    private String goodsName;
    /**
     * 商品规格
     */
    private String goodsSpecs;
    /**
     * 商品单位
     */
    private Integer company;
    /**
     * 采购单价
     */
    private Object monovalent;
    /**
     * 采购数量
     */
    private Integer quantity;
    /**
     * 税金
     */
    private Object taxes;
    /**
     * 采购总价
     */
    private Object total;
    /**
     * 商品分类名称
     */
    private String categoryName;
    /**
     * 品牌名称
     */
    private String brandName;

}
