package com.dofast.module.purchase.dal.dataobject.goods;

import lombok.*;

import java.math.BigDecimal;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import com.dofast.framework.mybatis.core.dataobject.BaseDO;
import org.apache.poi.hpsf.Decimal;

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
     * 采购单价
     */
    private Object monovalent;
    /**
     * 采购数量
     */
    private BigDecimal quantity;
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
    /**
     * 商品单位
     */
    private String company;
    /**
     * 采购订单号
     */
    private String poNo;
    /**
     * 入库数量
     */
    private BigDecimal receiveNum;
    /**
     * 入库单位
     */
    private String unitOfMeasure;
    /**
     * 状态（0-未打印，1-已打印，2-已入库）
     */
    private Integer status;
    /**
     * 收货时间
     */
    private LocalDateTime receiveTime;
    /**
     * 批次
     */
    private String batchCode;
    /**
     * 母批次
     */
    private String parentBatchCode;
    /**
     * 项次
     */
    private String consequence;

    /**
     * ERP数量
     */
    private BigDecimal erpNum;
}
