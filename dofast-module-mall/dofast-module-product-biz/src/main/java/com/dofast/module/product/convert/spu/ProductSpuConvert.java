package com.dofast.module.product.convert.spu;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.dofast.framework.common.pojo.PageResult;
import com.dofast.framework.common.util.collection.CollectionUtils;
import com.dofast.framework.dict.core.util.DictFrameworkUtils;


import com.dofast.module.product.controller.admin.spu.vo.*;

import com.dofast.module.product.api.spu.dto.ProductSpuRespDTO;

import com.dofast.module.product.controller.app.spu.vo.AppProductSpuDetailRespVO;
import com.dofast.module.product.controller.app.spu.vo.AppProductSpuPageReqVO;
import com.dofast.module.product.controller.app.spu.vo.AppProductSpuPageRespVO;
import com.dofast.module.product.convert.sku.ProductSkuConvert;
import com.dofast.module.product.dal.dataobject.sku.ProductSkuDO;
import com.dofast.module.product.dal.dataobject.spu.ProductSpuDO;
import com.dofast.module.product.enums.DictTypeConstants;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static cn.hutool.core.util.ObjectUtil.defaultIfNull;
import static com.dofast.framework.common.util.collection.CollectionUtils.convertMultiMap;

/**
 * 商品 SPU Convert
 *
 * @author 芋道源码
 */
@Mapper
public interface ProductSpuConvert {

    ProductSpuConvert INSTANCE = Mappers.getMapper(ProductSpuConvert.class);

    ProductSpuDO convert(ProductSpuCreateReqVO bean);

    ProductSpuDO convert(ProductSpuUpdateReqVO bean);

    List<ProductSpuDO> convertList(List<ProductSpuDO> list);

    PageResult<ProductSpuRespVO> convertPage(PageResult<ProductSpuDO> page);

    ProductSpuPageReqVO convert(AppProductSpuPageReqVO bean);

    List<ProductSpuRespDTO> convertList2(List<ProductSpuDO> list);

    default List<ProductSpuSimpleRespVO> convertList02(List<ProductSpuDO> list){
        if ( list == null ) {
            return null;
        }

        List<ProductSpuSimpleRespVO> list1 = new ArrayList<ProductSpuSimpleRespVO>( list.size() );
        for ( ProductSpuDO productSpuDO : list ) {
            list1.add( productSpuDOToProductSpuSimpleRespVO1( productSpuDO ) );
        }

        return list1;
    }

    default ProductSpuSimpleRespVO productSpuDOToProductSpuSimpleRespVO1(ProductSpuDO productSpuDO) {
        if ( productSpuDO == null ) {
            return null;
        }

        ProductSpuSimpleRespVO productSpuSimpleRespVO = new ProductSpuSimpleRespVO();

        productSpuSimpleRespVO.setCode( productSpuDO.getCode() );
        productSpuSimpleRespVO.setSellPoint( productSpuDO.getSellPoint() );
        productSpuSimpleRespVO.setDescription( productSpuDO.getDescription() );
        productSpuSimpleRespVO.setCategoryId( productSpuDO.getCategoryId() );
        productSpuSimpleRespVO.setBrandId( productSpuDO.getBrandId() );
        List<String> list = productSpuDO.getPicUrls();
        if ( list != null ) {
            productSpuSimpleRespVO.setPicUrls( new ArrayList<String>( list ) );
        }
        productSpuSimpleRespVO.setVideoUrl( productSpuDO.getVideoUrl() );
        productSpuSimpleRespVO.setSort( productSpuDO.getSort() );
        productSpuSimpleRespVO.setStatus( productSpuDO.getStatus() );
        productSpuSimpleRespVO.setSpecType( productSpuDO.getSpecType() );
        productSpuSimpleRespVO.setMarketPrice( productSpuDO.getMarketPrice() );
        productSpuSimpleRespVO.setVirtualSalesCount( productSpuDO.getVirtualSalesCount() );
        productSpuSimpleRespVO.setId( productSpuDO.getId() );
        productSpuSimpleRespVO.setName( productSpuDO.getName() );
        productSpuSimpleRespVO.setMinPrice( productSpuDO.getMinPrice() );

        return productSpuSimpleRespVO;
    }

    @Mapping(target = "price", expression = "java(spu.getPrice() / 100)")
    @Mapping(target = "marketPrice", expression = "java(spu.getMarketPrice() / 100)")
    @Mapping(target = "costPrice", expression = "java(spu.getCostPrice() / 100)")
    ProductSpuExcelVO convert(ProductSpuDO spu);

    default List<ProductSpuExcelVO> convertList03(List<ProductSpuDO> list) {
        List<ProductSpuExcelVO> spuExcelVOs = new ArrayList<>();
        list.forEach(spu -> {
            ProductSpuExcelVO spuExcelVO = convert(spu);
            spuExcelVOs.add(spuExcelVO);
        });
        return spuExcelVOs;
    }

    default ProductSpuDetailRespVO convert03(ProductSpuDO spu){
        if ( spu == null ) {
            return null;
        }

        ProductSpuDetailRespVO productSpuDetailRespVO = new ProductSpuDetailRespVO();

        productSpuDetailRespVO.setName( spu.getName() );
        productSpuDetailRespVO.setKeyword( spu.getKeyword() );
        productSpuDetailRespVO.setIntroduction( spu.getIntroduction() );
        productSpuDetailRespVO.setDescription( spu.getDescription() );
        productSpuDetailRespVO.setCategoryId( spu.getCategoryId() );
        productSpuDetailRespVO.setBrandId( spu.getBrandId() );
        List<String> list = spu.getSliderPicUrls();
        List<String> picUrls = spu.getPicUrls();
        productSpuDetailRespVO.setPicUrls(picUrls);
        String picUrl = spu.getPicUrl();
        productSpuDetailRespVO.setPicUrl(picUrl);
        if ( list != null ) {
            productSpuDetailRespVO.setSliderPicUrls( new ArrayList<String>( list ) );
        }
        productSpuDetailRespVO.setVideoUrl( spu.getVideoUrl() );
        productSpuDetailRespVO.setUnit( spu.getUnit() );
        productSpuDetailRespVO.setSort( spu.getSort() );
        productSpuDetailRespVO.setSpecType( spu.getSpecType() );
        productSpuDetailRespVO.setDeliveryTemplateId( spu.getDeliveryTemplateId() );
        productSpuDetailRespVO.setRecommendHot( spu.getRecommendHot() );
        productSpuDetailRespVO.setRecommendBenefit( spu.getRecommendBenefit() );
        productSpuDetailRespVO.setRecommendBest( spu.getRecommendBest() );
        productSpuDetailRespVO.setRecommendNew( spu.getRecommendNew() );
        productSpuDetailRespVO.setRecommendGood( spu.getRecommendGood() );
        productSpuDetailRespVO.setGiveIntegral( spu.getGiveIntegral() );
        List<Long> list1 = spu.getGiveCouponTemplateIds();
        if ( list1 != null ) {
            productSpuDetailRespVO.setGiveCouponTemplateIds( new ArrayList<Long>( list1 ) );
        }
        productSpuDetailRespVO.setSubCommissionType( spu.getSubCommissionType() );
        List<Integer> list2 = spu.getActivityOrders();
        if ( list2 != null ) {
            productSpuDetailRespVO.setActivityOrders( new ArrayList<Integer>( list2 ) );
        }
        productSpuDetailRespVO.setVirtualSalesCount( spu.getVirtualSalesCount() );
        productSpuDetailRespVO.setId( spu.getId() );
        productSpuDetailRespVO.setSalesCount( spu.getSalesCount() );
        productSpuDetailRespVO.setBrowseCount( spu.getBrowseCount() );
        productSpuDetailRespVO.setStatus( spu.getStatus() );
        return productSpuDetailRespVO;
    }

    ProductSpuRespDTO convert02(ProductSpuDO bean);

    // === 用户 App 相关 ===

    PageResult<AppProductSpuPageRespVO> convertPageForGetSpuPage(PageResult<ProductSpuDO> page);

    default List<AppProductSpuPageRespVO> convertListForGetSpuList(List<ProductSpuDO> list) {
        // 处理虚拟销量
        list.forEach(spu -> spu.setSalesCount(spu.getSalesCount() + spu.getVirtualSalesCount()));
        // 处理 VO 字段
        List<AppProductSpuPageRespVO> voList = convertListForGetSpuList0(list);
        for (int i = 0; i < list.size(); i++) {
            ProductSpuDO spu = list.get(i);
            AppProductSpuPageRespVO spuVO = voList.get(i);


            spuVO.setUnitName(DictFrameworkUtils.getDictDataLabel(DictTypeConstants.PRODUCT_UNIT, String.valueOf(spu.getUnit())));
            spuVO.setUnitName(DictFrameworkUtils.getDictDataLabel(DictTypeConstants.PRODUCT_UNIT, spu.getUnit()));


            spuVO.setUnitName(DictFrameworkUtils.getDictDataLabel(DictTypeConstants.PRODUCT_UNIT, String.valueOf(spu.getUnit())));

        }
        return voList;
    }

    @Named("convertListForGetSpuList0")
    List<AppProductSpuPageRespVO> convertListForGetSpuList0(List<ProductSpuDO> list);

    default AppProductSpuDetailRespVO convertForGetSpuDetail(ProductSpuDO spu, List<ProductSkuDO> skus) {
        // 处理 SPU
        AppProductSpuDetailRespVO spuVO = convertForGetSpuDetail(spu)
                .setSalesCount(spu.getSalesCount() + defaultIfNull(spu.getVirtualSalesCount(), 0))
                .setUnitName(DictFrameworkUtils.getDictDataLabel(DictTypeConstants.PRODUCT_UNIT, spu.getUnit()));
        // 处理 SKU
        spuVO.setSkus(convertListForGetSpuDetail(skus));
        return spuVO;
    }

    AppProductSpuDetailRespVO convertForGetSpuDetail(ProductSpuDO spu);

    List<AppProductSpuDetailRespVO.Sku> convertListForGetSpuDetail(List<ProductSkuDO> skus);

    default List<ProductSpuDetailRespVO> convertForSpuDetailRespListVO(List<ProductSpuDO> spus, List<ProductSkuDO> skus) {
        Map<Long, List<ProductSkuDO>> skuMultiMap = convertMultiMap(skus, ProductSkuDO::getSpuId);
        return CollectionUtils.convertList(spus, spu -> convert03(spu)
                .setSkus(ProductSkuConvert.INSTANCE.convertList(skuMultiMap.get(spu.getId()))));
    }

    default ProductSpuDetailRespVO convertForSpuDetailRespVO(ProductSpuDO spu, List<ProductSkuDO> skus) {
        return convert03(spu).setSkus(ProductSkuConvert.INSTANCE.convertList(skus));
    }

}
