package com.dofast.module.iot.dal.mysql.productauthorize;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;
import com.dofast.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.dofast.framework.mybatis.core.mapper.BaseMapperX;
import com.dofast.module.iot.dal.dataobject.productauthorize.ProductAuthorizeDO;
import org.apache.ibatis.annotations.Mapper;
import com.dofast.module.iot.controller.admin.productauthorize.vo.*;

/**
 * 产品授权码 Mapper
 *
 * @author 惠智造
 */
@Mapper
public interface ProductAuthorizeMapper extends BaseMapperX<ProductAuthorizeDO> {

    default PageResult<ProductAuthorizeDO> selectPage(ProductAuthorizePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ProductAuthorizeDO>()
                .eqIfPresent(ProductAuthorizeDO::getAuthorizeCode, reqVO.getAuthorizeCode())
                .eqIfPresent(ProductAuthorizeDO::getProductId, reqVO.getProductId())
                .eqIfPresent(ProductAuthorizeDO::getDeviceId, reqVO.getDeviceId())
                .eqIfPresent(ProductAuthorizeDO::getSerialNumber, reqVO.getSerialNumber())
                .eqIfPresent(ProductAuthorizeDO::getUserId, reqVO.getUserId())
                .likeIfPresent(ProductAuthorizeDO::getUserName, reqVO.getUserName())
                .eqIfPresent(ProductAuthorizeDO::getStatus, reqVO.getStatus())
                .eqIfPresent(ProductAuthorizeDO::getRemark, reqVO.getRemark())
                .betweenIfPresent(ProductAuthorizeDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(ProductAuthorizeDO::getId));
    }

    default List<ProductAuthorizeDO> selectList(ProductAuthorizeExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<ProductAuthorizeDO>()
                .eqIfPresent(ProductAuthorizeDO::getAuthorizeCode, reqVO.getAuthorizeCode())
                .eqIfPresent(ProductAuthorizeDO::getProductId, reqVO.getProductId())
                .eqIfPresent(ProductAuthorizeDO::getDeviceId, reqVO.getDeviceId())
                .eqIfPresent(ProductAuthorizeDO::getSerialNumber, reqVO.getSerialNumber())
                .eqIfPresent(ProductAuthorizeDO::getUserId, reqVO.getUserId())
                .likeIfPresent(ProductAuthorizeDO::getUserName, reqVO.getUserName())
                .eqIfPresent(ProductAuthorizeDO::getStatus, reqVO.getStatus())
                .eqIfPresent(ProductAuthorizeDO::getRemark, reqVO.getRemark())
                .betweenIfPresent(ProductAuthorizeDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(ProductAuthorizeDO::getId));
    }

}
