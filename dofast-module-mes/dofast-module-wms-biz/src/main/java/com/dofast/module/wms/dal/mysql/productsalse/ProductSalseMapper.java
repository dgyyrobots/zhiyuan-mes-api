package com.dofast.module.wms.dal.mysql.productsalse;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;
import com.dofast.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.dofast.framework.mybatis.core.mapper.BaseMapperX;
import com.dofast.module.wms.dal.dataobject.productsalse.ProductSalseDO;
import com.dofast.module.wms.dal.dataobject.productsalse.ProductSalseTxBean;
import org.apache.ibatis.annotations.Mapper;
import com.dofast.module.wms.controller.admin.productsalse.vo.*;

/**
 * 销售出库单 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface ProductSalseMapper extends BaseMapperX<ProductSalseDO> {
    /**
     * 获取产品销售出库事务Bean
     * @param salseId
     * @return
     */
    List<ProductSalseTxBean> getTxBeans(Long salseId);

    default ProductSalseDO checkUnique(ProductSalseBaseVO baseVO){
        return selectOne(new LambdaQueryWrapperX<ProductSalseDO>().eq(ProductSalseDO::getSalseCode,baseVO.getSalseCode()));
    }
    default PageResult<ProductSalseDO> selectPage(ProductSalsePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ProductSalseDO>()
                .eqIfPresent(ProductSalseDO::getSalseCode, reqVO.getSalseCode())
                .likeIfPresent(ProductSalseDO::getSalseName, reqVO.getSalseName())
                .eqIfPresent(ProductSalseDO::getOqcId, reqVO.getOqcId())
                .eqIfPresent(ProductSalseDO::getOqcCode, reqVO.getOqcCode())
                .eqIfPresent(ProductSalseDO::getSoCode, reqVO.getSoCode())
                .eqIfPresent(ProductSalseDO::getClientId, reqVO.getClientId())
                .eqIfPresent(ProductSalseDO::getClientCode, reqVO.getClientCode())
                .likeIfPresent(ProductSalseDO::getClientName, reqVO.getClientName())
                .eqIfPresent(ProductSalseDO::getClientNick, reqVO.getClientNick())
                .eqIfPresent(ProductSalseDO::getWarehouseId, reqVO.getWarehouseId())
                .eqIfPresent(ProductSalseDO::getWarehouseCode, reqVO.getWarehouseCode())
                .likeIfPresent(ProductSalseDO::getWarehouseName, reqVO.getWarehouseName())
                .eqIfPresent(ProductSalseDO::getLocationId, reqVO.getLocationId())
                .eqIfPresent(ProductSalseDO::getLocationCode, reqVO.getLocationCode())
                .likeIfPresent(ProductSalseDO::getLocationName, reqVO.getLocationName())
                .eqIfPresent(ProductSalseDO::getAreaId, reqVO.getAreaId())
                .eqIfPresent(ProductSalseDO::getAreaCode, reqVO.getAreaCode())
                .likeIfPresent(ProductSalseDO::getAreaName, reqVO.getAreaName())
                .betweenIfPresent(ProductSalseDO::getSalseDate, reqVO.getSalseDate())
                .eqIfPresent(ProductSalseDO::getStatus, reqVO.getStatus())
                .eqIfPresent(ProductSalseDO::getRemark, reqVO.getRemark())
                .eqIfPresent(ProductSalseDO::getAttr1, reqVO.getAttr1())
                .eqIfPresent(ProductSalseDO::getAttr2, reqVO.getAttr2())
                .eqIfPresent(ProductSalseDO::getAttr3, reqVO.getAttr3())
                .eqIfPresent(ProductSalseDO::getAttr4, reqVO.getAttr4())
                .betweenIfPresent(ProductSalseDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(ProductSalseDO::getId));
    }

    default List<ProductSalseDO> selectList(ProductSalseExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<ProductSalseDO>()
                .eqIfPresent(ProductSalseDO::getSalseCode, reqVO.getSalseCode())
                .likeIfPresent(ProductSalseDO::getSalseName, reqVO.getSalseName())
                .eqIfPresent(ProductSalseDO::getOqcId, reqVO.getOqcId())
                .eqIfPresent(ProductSalseDO::getOqcCode, reqVO.getOqcCode())
                .eqIfPresent(ProductSalseDO::getSoCode, reqVO.getSoCode())
                .eqIfPresent(ProductSalseDO::getClientId, reqVO.getClientId())
                .eqIfPresent(ProductSalseDO::getClientCode, reqVO.getClientCode())
                .likeIfPresent(ProductSalseDO::getClientName, reqVO.getClientName())
                .eqIfPresent(ProductSalseDO::getClientNick, reqVO.getClientNick())
                .eqIfPresent(ProductSalseDO::getWarehouseId, reqVO.getWarehouseId())
                .eqIfPresent(ProductSalseDO::getWarehouseCode, reqVO.getWarehouseCode())
                .likeIfPresent(ProductSalseDO::getWarehouseName, reqVO.getWarehouseName())
                .eqIfPresent(ProductSalseDO::getLocationId, reqVO.getLocationId())
                .eqIfPresent(ProductSalseDO::getLocationCode, reqVO.getLocationCode())
                .likeIfPresent(ProductSalseDO::getLocationName, reqVO.getLocationName())
                .eqIfPresent(ProductSalseDO::getAreaId, reqVO.getAreaId())
                .eqIfPresent(ProductSalseDO::getAreaCode, reqVO.getAreaCode())
                .likeIfPresent(ProductSalseDO::getAreaName, reqVO.getAreaName())
                .betweenIfPresent(ProductSalseDO::getSalseDate, reqVO.getSalseDate())
                .eqIfPresent(ProductSalseDO::getStatus, reqVO.getStatus())
                .eqIfPresent(ProductSalseDO::getRemark, reqVO.getRemark())
                .eqIfPresent(ProductSalseDO::getAttr1, reqVO.getAttr1())
                .eqIfPresent(ProductSalseDO::getAttr2, reqVO.getAttr2())
                .eqIfPresent(ProductSalseDO::getAttr3, reqVO.getAttr3())
                .eqIfPresent(ProductSalseDO::getAttr4, reqVO.getAttr4())
                .betweenIfPresent(ProductSalseDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(ProductSalseDO::getId));
    }

}
