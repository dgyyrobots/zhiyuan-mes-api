package com.dofast.module.promotion.convert.discount;

import cn.hutool.core.util.ObjectUtil;
import com.dofast.framework.common.pojo.PageResult;
import com.dofast.framework.common.util.collection.CollectionUtils;
import com.dofast.framework.common.util.collection.MapUtils;


import com.dofast.module.product.api.spu.dto.ProductSpuRespDTO;



import com.dofast.module.promotion.api.discount.dto.DiscountProductRespDTO;
import com.dofast.module.promotion.controller.admin.discount.vo.*;
import com.dofast.module.promotion.dal.dataobject.discount.DiscountActivityDO;
import com.dofast.module.promotion.dal.dataobject.discount.DiscountProductDO;
import com.dofast.module.promotion.enums.common.PromotionDiscountTypeEnum;

import com.dofast.module.promotion.service.discount.bo.DiscountProductDetailBO;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Map;

/**
 * 限时折扣活动 Convert
 *
 * @author 芋道源码
 */
@Mapper
public interface DiscountActivityConvert {

    DiscountActivityConvert INSTANCE = Mappers.getMapper(DiscountActivityConvert.class);

    DiscountActivityDO convert(DiscountActivityCreateReqVO bean);

    DiscountActivityDO convert(DiscountActivityUpdateReqVO bean);

    DiscountActivityRespVO convert(DiscountActivityDO bean);

    List<DiscountActivityRespVO> convertList(List<DiscountActivityDO> list);

    default List<DiscountProductDetailBO> convertList1(List<DiscountProductDO> products, Map<Long, DiscountActivityDO> activityMap) {
        return CollectionUtils.convertList(products, product -> {
            DiscountProductDetailBO detail = convert(product);
            MapUtils.findAndThen(activityMap, product.getActivityId(), activity -> {
                detail.setActivityName(activity.getName());
            });
            return detail;
        });
    }

    DiscountProductDetailBO convert(DiscountProductDO product);

    List<DiscountProductRespDTO> convertList02(List<DiscountProductDO> list);

    PageResult<DiscountActivityRespVO> convertPage(PageResult<DiscountActivityDO> page);

    DiscountProductDO convert(DiscountActivityBaseVO.Product bean);


    // =========== 比较是否相等 ==========
    /**
     * 比较两个限时折扣商品是否相等
     *
     * @param productDO 数据库中的商品
     * @param productVO 前端传入的商品
     * @return 是否匹配
     */
    @SuppressWarnings("DuplicatedCode")
    default boolean isEquals(DiscountProductDO productDO, DiscountActivityBaseVO.Product productVO) {
        if (ObjectUtil.notEqual(productDO.getSpuId(), productVO.getSpuId())
                || ObjectUtil.notEqual(productDO.getSkuId(), productVO.getSkuId())
                || ObjectUtil.notEqual(productDO.getDiscountType(), productVO.getDiscountType())) {
            return false;
        }
        if (productDO.getDiscountType().equals(PromotionDiscountTypeEnum.PRICE.getType())) {
            return ObjectUtil.equal(productDO.getDiscountPrice(), productVO.getDiscountPrice());
        }
        if (productDO.getDiscountType().equals(PromotionDiscountTypeEnum.PERCENT.getType())) {
            return ObjectUtil.equal(productDO.getDiscountPercent(), productVO.getDiscountPercent());
        }
        return true;
    }

    /**
     * 比较两个限时折扣商品是否相等
     * 注意，比较时忽略 id 编号
     *
     * @param productDO 商品 1
     * @param productVO 商品 2
     * @return 是否匹配
     */
    @SuppressWarnings("DuplicatedCode")
    default boolean isEquals(DiscountProductDO productDO, DiscountProductDO productVO) {
        if (ObjectUtil.notEqual(productDO.getSpuId(), productVO.getSpuId())
                || ObjectUtil.notEqual(productDO.getSkuId(), productVO.getSkuId())
                || ObjectUtil.notEqual(productDO.getDiscountType(), productVO.getDiscountType())) {
            return false;
        }
        if (productDO.getDiscountType().equals(PromotionDiscountTypeEnum.PRICE.getType())) {
            return ObjectUtil.equal(productDO.getDiscountPrice(), productVO.getDiscountPrice());
        }
        if (productDO.getDiscountType().equals(PromotionDiscountTypeEnum.PERCENT.getType())) {
            return ObjectUtil.equal(productDO.getDiscountPercent(), productVO.getDiscountPercent());
        }
        return true;
    }





    List<DiscountActivityBaseVO.Product> convertList2(List<DiscountProductDO> list);


    default PageResult<DiscountActivityRespVO> convertPage(PageResult<DiscountActivityDO> page,
                                                           List<DiscountProductDO> discountProductDOList,
                                                           List<ProductSpuRespDTO> spuList) {
        PageResult<DiscountActivityRespVO> pageResult = convertPage(page);

        // 拼接商品 TODO @zhangshuai：类似空行的问题，也可以看看
        Map<Long, DiscountProductDO> discountActivityMap = CollectionUtils.convertMap(discountProductDOList, DiscountProductDO::getActivityId);
        Map<Long, ProductSpuRespDTO> spuMap = CollectionUtils.convertMap(spuList, ProductSpuRespDTO::getId);
        pageResult.getList().forEach(item -> {
            item.setProducts(convertList2(discountProductDOList));
            item.setSpuId(discountActivityMap.get(item.getId())==null?null: discountActivityMap.get(item.getId()).getSpuId());
            if (item.getSpuId() != null) {
                MapUtils.findAndThen(spuMap, item.getSpuId(),
                        spu -> item.setSpuName(spu.getName()).setPicUrl(spu.getPicUrl()).setMarketPrice(spu.getMarketPrice()));
            }

        });
        return pageResult;
    }

    default DiscountActivityDetailRespVO convert(DiscountActivityDO activity, List<DiscountProductDO> products){
        if ( activity == null && products == null ) {
            return null;
        }

        DiscountActivityDetailRespVO discountActivityDetailRespVO = new DiscountActivityDetailRespVO();

        if ( activity != null ) {
            discountActivityDetailRespVO.setName( activity.getName() );
            discountActivityDetailRespVO.setStartTime( activity.getStartTime() );
            discountActivityDetailRespVO.setEndTime( activity.getEndTime() );
            discountActivityDetailRespVO.setRemark( activity.getRemark() );
            discountActivityDetailRespVO.setId( activity.getId() );
            discountActivityDetailRespVO.setStatus( activity.getStatus() );
            discountActivityDetailRespVO.setCreateTime( activity.getCreateTime() );
        }
        if (!products.isEmpty()) {
            discountActivityDetailRespVO.setSpuId(products.get(0).getSpuId());
        }
        discountActivityDetailRespVO.setProducts( convertList2( products ) );

        return discountActivityDetailRespVO;
    }

    // =========== 比较是否相等 ==========

}
