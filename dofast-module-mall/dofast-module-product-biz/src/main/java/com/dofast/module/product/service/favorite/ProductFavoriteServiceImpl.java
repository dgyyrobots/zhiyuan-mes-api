package com.dofast.module.product.service.favorite;



import com.dofast.framework.common.exception.util.ServiceExceptionUtil;


import com.dofast.framework.common.exception.util.ServiceExceptionUtil;

import com.dofast.framework.common.pojo.PageResult;
import com.dofast.module.product.controller.app.favorite.vo.AppFavoritePageReqVO;
import com.dofast.module.product.convert.favorite.ProductFavoriteConvert;
import com.dofast.module.product.dal.dataobject.favorite.ProductFavoriteDO;
import com.dofast.module.product.dal.mysql.favorite.ProductFavoriteMapper;


import com.dofast.module.product.enums.ErrorCodeConstants;


import com.dofast.module.product.enums.ErrorCodeConstants;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import javax.validation.Valid;



import java.util.List;

import static com.dofast.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.dofast.module.product.enums.ErrorCodeConstants.FAVORITE_EXISTS;
import static com.dofast.module.product.enums.ErrorCodeConstants.FAVORITE_NOT_EXISTS;


import java.util.List;

import static com.dofast.framework.common.exception.util.ServiceExceptionUtil.exception;


/**
 * 商品收藏 Service 实现类
 *
 * @author jason
 */
@Service
@Validated
public class ProductFavoriteServiceImpl implements ProductFavoriteService {

    @Resource
    private ProductFavoriteMapper productFavoriteMapper;

    @Override
    public Long createFavorite(Long userId, Long spuId) {
        ProductFavoriteDO favorite = productFavoriteMapper.selectByUserIdAndSpuId(userId, spuId);
        if (favorite != null) {

            throw exception(FAVORITE_EXISTS);
        }

        ProductFavoriteDO entity = ProductFavoriteConvert.INSTANCE.convert(userId, spuId);
        productFavoriteMapper.insert(entity);
        return entity.getId();
    }

    @Override
    public void deleteFavorite(Long userId, Long spuId) {
        ProductFavoriteDO favorite = productFavoriteMapper.selectByUserIdAndSpuId(userId, spuId);
        if (favorite == null) {

            throw exception(FAVORITE_NOT_EXISTS);

        }

        productFavoriteMapper.deleteById(favorite.getId());
    }

    @Override
    public void deleteFavorites(Long userId, List<Long> spuIds) {
        productFavoriteMapper.deleteAll(spuIds);
    }




    @Override

    public PageResult<ProductFavoriteDO> getFavoritePage(Long userId, @Valid AppFavoritePageReqVO reqVO) {
        return productFavoriteMapper.selectPageByUserAndType(userId, reqVO);
    }

    @Override
    public ProductFavoriteDO getFavorite(Long userId, Long spuId) {
        return productFavoriteMapper.selectByUserIdAndSpuId(userId, spuId);
    }

    @Override
    public Long getFavoriteCount(Long userId) {
        return productFavoriteMapper.selectCountByUserId(userId);
    }

}
