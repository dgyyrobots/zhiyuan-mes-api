package com.dofast.module.product.dal.mysql.favorite;

import com.dofast.framework.common.pojo.PageResult;
import com.dofast.framework.mybatis.core.mapper.BaseMapperX;




import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.dofast.module.product.controller.app.favorite.vo.AppFavoritePageReqVO;
import com.dofast.module.product.dal.dataobject.favorite.ProductFavoriteDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

import com.dofast.module.product.controller.app.favorite.vo.AppFavoritePageReqVO;
import com.dofast.module.product.dal.dataobject.favorite.ProductFavoriteDO;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.apache.ibatis.annotations.Mapper;




@Mapper
public interface ProductFavoriteMapper extends BaseMapperX<ProductFavoriteDO> {

    default ProductFavoriteDO selectByUserIdAndSpuId(Long userId, Long spuId) {
        return selectOne(ProductFavoriteDO::getUserId, userId,
                ProductFavoriteDO::getSpuId, spuId);
    }

    default PageResult<ProductFavoriteDO> selectPageByUserAndType(Long userId, AppFavoritePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapper<ProductFavoriteDO>()
                .eq(ProductFavoriteDO::getUserId, userId)
                .orderByDesc(ProductFavoriteDO::getId));
    }

    default Long selectCountByUserId(Long userId) {
        return selectCount(ProductFavoriteDO::getUserId, userId);
    }





    default void deleteAll(List<Long> spuIds){
        deleteBatchIds(spuIds);
    }





}
