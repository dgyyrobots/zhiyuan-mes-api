package com.dofast.module.product.controller.admin.spu.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.dofast.framework.common.validation.InEnum;
import com.dofast.module.product.dal.dataobject.brand.ProductBrandDO;
import com.dofast.module.product.dal.dataobject.category.ProductCategoryDO;
import com.dofast.module.product.dal.dataobject.sku.ProductSkuDO;
import com.dofast.module.product.enums.spu.ProductSpuStatusEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Schema(description = "管理后台 - 商品 SPU 精简 Response VO")
@Data
@ToString(callSuper = true)
public class ProductSpuSimpleRespVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "213")
    private Long id;

    @Schema(description = "商品名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "清凉小短袖")
    private String name;

    @Schema(description = "商品价格，单位使用：分", requiredMode = Schema.RequiredMode.REQUIRED, example = "1999")
    private Integer price;



    @Schema(description = "商品市场价，单位使用：分", requiredMode = Schema.RequiredMode.REQUIRED, example = "199")
    private Integer marketPrice;

    @Schema(description = "商品成本价，单位使用：分", requiredMode = Schema.RequiredMode.REQUIRED, example = "19")
    private Integer costPrice;

    @Schema(description = "商品库存", requiredMode = Schema.RequiredMode.REQUIRED, example = "2000")
    private Integer stock;

    // === 统计相关字段 ==

    @Schema(description = "商品销量", requiredMode = Schema.RequiredMode.REQUIRED, example = "200")
    private Integer salesCount;

    @Schema(description = "商品虚拟销量", requiredMode = Schema.RequiredMode.REQUIRED, example = "20000")
    private Integer virtualSalesCount;

    @Schema(description = "商品浏览量", requiredMode = Schema.RequiredMode.REQUIRED, example = "2000")
    private Integer browseCount;


    /**
     * 关键字
     */
    @Schema(description = "关键字")
    private String keyword;
    /**
     * 商品简介
     */

    @Schema(description = "商品简介")
    private String introduction;
    /**
     * 商品详情
     */

    @Schema(description = "商品详情", example = "2000")
    private String description;
    // TODO @芋艿：是不是要删除
    /**
     * 商品条码（一维码）
     */

    @Schema(description = "商品条码（一维码）", example = "2000")
    private String barCode;

    /**
     * 商品分类编号
     *
     * 关联 {@link ProductCategoryDO#getId()}
     */

    @Schema(description = "商品分类编号", example = "2000")
    private Long categoryId;
    /**
     * 商品品牌编号
     *
     * 关联 {@link ProductBrandDO#getId()}
     */
    @Schema(description = "商品品牌编号", example = "2000")
    private Long brandId;

    /**
     * 商品轮播图
     */
    @Schema(description = "商品轮播图", example = "2000")
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<String> sliderPicUrls;
    /**
     * 商品视频
     */
    @Schema(description = "商品视频", example = "2000")
    private String videoUrl;

    /**
     * 单位
     *
     * 对应 product_unit 数据字典
     */
    @Schema(description = "单位", example = "2000")
    private Integer unit;
    /**
     * 排序字段
     */
    @Schema(description = "排序字段", example = "2000")
    private Integer sort;
    /**
     * 商品状态
     *
     * 枚举 {@link ProductSpuStatusEnum}
     */
    @Schema(description = "商品状态", example = "2000")
    private Integer status;

    // === SKU 相关字段 ==

    /**
     * 规格类型
     *
     * false - 单规格
     * true - 多规格
     */
    @Schema(description = "规格类型", example = "2000")
    private Integer specType;

    // === 物流相关字段 ==

    /**
     * 物流配置模板编号
     *
     * 对应 TradeDeliveryExpressTemplateDO 的 id 编号
     */
    @Schema(description = "物流配置模板编号", example = "2000")
    private Long deliveryTemplateId;

    // === 营销相关字段 ==
    /**
     * 是否热卖推荐
     */
    @Schema(description = "是否热卖推荐", example = "2000")
    private Integer recommendHot;
    /**
     * 是否优惠推荐
     */
    @Schema(description = "是否优惠推荐", example = "2000")
    private Integer recommendBenefit;
    /**
     * 是否精品推荐
     */
    @Schema(description = "是否精品推荐", example = "2000")
    private Integer recommendBest;
    /**
     *
     */
    @Schema(description = "是否新品推荐", example = "2000")
    private Integer recommendNew;
    /**
     * 是否优品推荐
     */
    @Schema(description = "是否优品推荐", example = "2000")
    private Integer recommendGood;

    /**
     * 赠送积分
     */
    @Schema(description = "赠送积分", example = "2000")
    private Integer giveIntegral;
    /**
     * 赠送的优惠劵编号的数组
     *
     * 对应 CouponTemplateDO 的 id 属性
     */
    @Schema(description = "赠送的优惠劵编号的数组", example = "2000")
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<Long> giveCouponTemplateIds;

    /**
     * 分销类型
     *
     * false - 默认
     * true - 自行设置
     */
    @Schema(description = "分销类型", example = "2000")
    private Integer subCommissionType;

    /**
     * 活动展示顺序
     *
     * 对应 PromotionTypeEnum 枚举
     */
    @Schema(description = "活动展示顺序", example = "2000")
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<Integer> activityOrders;


    @Schema(description = " 最小价格，单位使用：分", requiredMode = Schema.RequiredMode.REQUIRED, example = "1024")
    private Integer minPrice;

    @Schema(description = "商品编码", example = "dofastyuanma")
    private String code;

    @Schema(description = "促销语", example = "好吃！")
    private String sellPoint;

    @Schema(description = "商品图片的数组", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "商品图片的数组不能为空")
    private List<String> picUrls;


}
