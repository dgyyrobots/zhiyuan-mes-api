package com.dofast.module.iot.dal.mysql.product;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;
import com.dofast.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.dofast.framework.mybatis.core.mapper.BaseMapperX;
import com.dofast.module.iot.dal.dataobject.product.ProductDO;
import org.apache.ibatis.annotations.Mapper;
import com.dofast.module.iot.controller.admin.product.vo.*;

/**
 * 产品 Mapper
 *
 * @author 惠智造
 */
@Mapper
public interface ProductMapper extends BaseMapperX<ProductDO> {

    default PageResult<ProductDO> selectPage(ProductPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ProductDO>()
                .likeIfPresent(ProductDO::getProductName, reqVO.getProductName())
                .eqIfPresent(ProductDO::getCategoryId, reqVO.getCategoryId())
                .likeIfPresent(ProductDO::getCategoryName, reqVO.getCategoryName())
                .eqIfPresent(ProductDO::getIsSys, reqVO.getIsSys())
                .eqIfPresent(ProductDO::getIsAuthorize, reqVO.getIsAuthorize())
                .eqIfPresent(ProductDO::getMqttAccount, reqVO.getMqttAccount())
                .eqIfPresent(ProductDO::getMqttPassword, reqVO.getMqttPassword())
                .eqIfPresent(ProductDO::getMqttSecret, reqVO.getMqttSecret())
                .eqIfPresent(ProductDO::getStatus, reqVO.getStatus())
                .eqIfPresent(ProductDO::getThingsModelsJson, reqVO.getThingsModelsJson())
                .eqIfPresent(ProductDO::getDeviceType, reqVO.getDeviceType())
                .eqIfPresent(ProductDO::getNetworkMethod, reqVO.getNetworkMethod())
                .eqIfPresent(ProductDO::getVertificateMethod, reqVO.getVertificateMethod())
                .eqIfPresent(ProductDO::getImgUrl, reqVO.getImgUrl())
                .eqIfPresent(ProductDO::getRemark, reqVO.getRemark())
                .betweenIfPresent(ProductDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(ProductDO::getId));
    }

    default List<ProductDO> selectList(ProductExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<ProductDO>()
                .likeIfPresent(ProductDO::getProductName, reqVO.getProductName())
                .eqIfPresent(ProductDO::getCategoryId, reqVO.getCategoryId())
                .likeIfPresent(ProductDO::getCategoryName, reqVO.getCategoryName())
                .eqIfPresent(ProductDO::getIsSys, reqVO.getIsSys())
                .eqIfPresent(ProductDO::getIsAuthorize, reqVO.getIsAuthorize())
                .eqIfPresent(ProductDO::getMqttAccount, reqVO.getMqttAccount())
                .eqIfPresent(ProductDO::getMqttPassword, reqVO.getMqttPassword())
                .eqIfPresent(ProductDO::getMqttSecret, reqVO.getMqttSecret())
                .eqIfPresent(ProductDO::getStatus, reqVO.getStatus())
                .eqIfPresent(ProductDO::getThingsModelsJson, reqVO.getThingsModelsJson())
                .eqIfPresent(ProductDO::getDeviceType, reqVO.getDeviceType())
                .eqIfPresent(ProductDO::getNetworkMethod, reqVO.getNetworkMethod())
                .eqIfPresent(ProductDO::getVertificateMethod, reqVO.getVertificateMethod())
                .eqIfPresent(ProductDO::getImgUrl, reqVO.getImgUrl())
                .eqIfPresent(ProductDO::getRemark, reqVO.getRemark())
                .betweenIfPresent(ProductDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(ProductDO::getId));
    }

}
