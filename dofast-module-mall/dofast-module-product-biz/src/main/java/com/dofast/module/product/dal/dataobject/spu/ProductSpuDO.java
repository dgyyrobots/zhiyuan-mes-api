package com.dofast.module.product.dal.dataobject.spu;

import com.dofast.framework.mybatis.core.dataobject.BaseDO;


import com.dofast.module.product.dal.dataobject.brand.ProductBrandDO;
import com.dofast.module.product.dal.dataobject.category.ProductCategoryDO;
import com.dofast.module.product.dal.dataobject.sku.ProductSkuDO;
import com.dofast.module.product.enums.spu.ProductSpuStatusEnum;



import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.dofast.module.product.dal.dataobject.brand.ProductBrandDO;
import com.dofast.module.product.dal.dataobject.category.ProductCategoryDO;
import com.dofast.module.product.dal.dataobject.sku.ProductSkuDO;
import com.dofast.module.product.enums.spu.ProductSpuStatusEnum;
import lombok.*;

import java.beans.Transient;
import java.util.List;

/**
 * 商品 SPU DO
 *
 * @author 芋道源码
 */
@TableName(value = "product_spu", autoResultMap = true)
@KeySequence("product_spu_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductSpuDO extends BaseDO {

    /**
     * 商品 SPU 编号，自增
     */
    @TableId
    private Long id;

    // === 基本信息 ==

    /**
     * 商品名称
     */
    private String name;
    /**
     * 关键字
     */
    private String keyword;
    /**
     * 商品简介
     */
    private String introduction;
    /**
     * 商品详情
     */
    private String description;
    // TODO @芋艿：是不是要删除
    /**
     * 商品条码（一维码）
     */
    private String barCode;

    /**
     * 商品分类编号
     *
     * 关联 {@link ProductCategoryDO#getId()}
     */
    private Long categoryId;
    /**
     * 商品品牌编号
     *
     * 关联 {@link ProductBrandDO#getId()}
     */
    private Long brandId;
    /**
     * 商品封面图

     */
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<String> picUrls;

    private String picUrl;


    private Integer minPrice;
    /**
     * 商品轮播图
     */
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<String> sliderPicUrls;
    /**
     * 商品视频
     */
    private String videoUrl;

    /**
     * 单位
     *
     * 对应 product_unit 数据字典
     */
    private Integer unit;
    /**
     * 排序字段
     */
    private Integer sort;
    /**
     * 商品状态
     *
     * 枚举 {@link ProductSpuStatusEnum}
     */
    private Integer status;

    // === SKU 相关字段 ==

    /**
     * 规格类型
     *
     * false - 单规格
     * true - 多规格
     */
    private Integer specType;
    /**
     * 商品价格，单位使用：分


     *
     * 基于其对应的 {@link ProductSkuDO#getPrice()} sku单价最低的商品的
     */
    private Integer price;
    /**
     * 市场价，单位使用：分
     *
     * 基于其对应的 {@link ProductSkuDO#getMarketPrice()} sku单价最低的商品的
     */
    private Integer marketPrice;
    /**
     * 成本价，单位使用：分
     *
     * 基于其对应的 {@link ProductSkuDO#getCostPrice()} sku单价最低的商品的
     */
    private Integer costPrice;
    /**
     * 库存
     *
     * 基于其对应的 {@link ProductSkuDO#getStock()} 求和
     */
    private Integer stock;

    // === 物流相关字段 ==

    /**
     * 物流配置模板编号
     *
     * 对应 TradeDeliveryExpressTemplateDO 的 id 编号
     */
    private Long deliveryTemplateId;

    // === 营销相关字段 ==
    /**
     * 是否热卖推荐
     */
    private Integer recommendHot;
    /**
     * 是否优惠推荐
     */
    private Integer recommendBenefit;
    /**
     * 是否精品推荐
     */
    private Integer recommendBest;
    /**
     * 是否新品推荐
     */
    private Integer recommendNew;
    /**
     * 是否优品推荐
     */
    private Integer recommendGood;

    /**
     * 赠送积分
     */
    private Integer giveIntegral;
    /**
     * 赠送的优惠劵编号的数组
     *
     * 对应 CouponTemplateDO 的 id 属性
     */
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<Long> giveCouponTemplateIds;

    /**
     * 分销类型
     *
     * false - 默认
     * true - 自行设置
     */
    private Integer subCommissionType;

    /**
     * 活动展示顺序
     *
     * 对应 PromotionTypeEnum 枚举
     */
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<Integer> activityOrders;

    // === 统计相关字段 ==

    /**
     * 商品销量
     */
    private Integer salesCount;
    /**
     * 虚拟销量
     */
    private Integer virtualSalesCount;
    /**
     * 浏览量
     */
    private Integer browseCount;


    /**
     * 商品编码
     */
    private String code;
    /**
     * 促销语
     */
    private String sellPoint;
    // === 物流相关字段 ==



    private Integer clickCount;

}
