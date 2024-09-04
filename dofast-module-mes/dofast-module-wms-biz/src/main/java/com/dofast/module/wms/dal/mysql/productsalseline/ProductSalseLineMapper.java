package com.dofast.module.wms.dal.mysql.productsalseline;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;
import com.dofast.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.dofast.framework.mybatis.core.mapper.BaseMapperX;
import com.dofast.module.wms.dal.dataobject.productsalseline.ProductSalseLineDO;
import org.apache.ibatis.annotations.Mapper;
import com.dofast.module.wms.controller.admin.productsalseline.vo.*;

/**
 * 产品销售出库行 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface ProductSalseLineMapper extends BaseMapperX<ProductSalseLineDO> {
    default List<ProductSalseLineDO> selectList(ProductSalseLineListVO listVO){
        return selectList(new LambdaQueryWrapperX<ProductSalseLineDO>()
                .eqIfPresent(ProductSalseLineDO::getSalseId, listVO.getSalseId())
                .eqIfPresent(ProductSalseLineDO::getMaterialStockId, listVO.getMaterialStockId())
                .eqIfPresent(ProductSalseLineDO::getItemId, listVO.getItemId())
                .eqIfPresent(ProductSalseLineDO::getItemCode, listVO.getItemCode())
                .likeIfPresent(ProductSalseLineDO::getItemName, listVO.getItemName())
                .eqIfPresent(ProductSalseLineDO::getSpecification, listVO.getSpecification())
                .eqIfPresent(ProductSalseLineDO::getUnitOfMeasure, listVO.getUnitOfMeasure())
                .eqIfPresent(ProductSalseLineDO::getQuantitySalse, listVO.getQuantitySalse())
                .eqIfPresent(ProductSalseLineDO::getBatchCode, listVO.getBatchCode())
                .eqIfPresent(ProductSalseLineDO::getWarehouseId, listVO.getWarehouseId())
                .eqIfPresent(ProductSalseLineDO::getWarehouseCode, listVO.getWarehouseCode())
                .likeIfPresent(ProductSalseLineDO::getWarehouseName, listVO.getWarehouseName())
                .eqIfPresent(ProductSalseLineDO::getLocationId, listVO.getLocationId())
                .eqIfPresent(ProductSalseLineDO::getLocationCode, listVO.getLocationCode())
                .likeIfPresent(ProductSalseLineDO::getLocationName, listVO.getLocationName())
                .eqIfPresent(ProductSalseLineDO::getAreaId, listVO.getAreaId())
                .eqIfPresent(ProductSalseLineDO::getAreaCode, listVO.getAreaCode())
                .likeIfPresent(ProductSalseLineDO::getAreaName, listVO.getAreaName())
                .eqIfPresent(ProductSalseLineDO::getRemark, listVO.getRemark())
                .eqIfPresent(ProductSalseLineDO::getAttr1, listVO.getAttr1())
                .eqIfPresent(ProductSalseLineDO::getAttr2, listVO.getAttr2())
                .eqIfPresent(ProductSalseLineDO::getAttr3, listVO.getAttr3())
                .eqIfPresent(ProductSalseLineDO::getAttr4, listVO.getAttr4())
                .orderByDesc(ProductSalseLineDO::getId)
        );
    }
    default int deleteBySalseId(Long salseId){
        return delete(new LambdaQueryWrapperX<ProductSalseLineDO>().eq(ProductSalseLineDO::getSalseId,salseId));
    }
    default PageResult<ProductSalseLineDO> selectPage(ProductSalseLinePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ProductSalseLineDO>()
                .eqIfPresent(ProductSalseLineDO::getSalseId, reqVO.getSalseId())
                .eqIfPresent(ProductSalseLineDO::getMaterialStockId, reqVO.getMaterialStockId())
                .eqIfPresent(ProductSalseLineDO::getItemId, reqVO.getItemId())
                .eqIfPresent(ProductSalseLineDO::getItemCode, reqVO.getItemCode())
                .likeIfPresent(ProductSalseLineDO::getItemName, reqVO.getItemName())
                .eqIfPresent(ProductSalseLineDO::getSpecification, reqVO.getSpecification())
                .eqIfPresent(ProductSalseLineDO::getUnitOfMeasure, reqVO.getUnitOfMeasure())
                .eqIfPresent(ProductSalseLineDO::getQuantitySalse, reqVO.getQuantitySalse())
                .eqIfPresent(ProductSalseLineDO::getBatchCode, reqVO.getBatchCode())
                .eqIfPresent(ProductSalseLineDO::getWarehouseId, reqVO.getWarehouseId())
                .eqIfPresent(ProductSalseLineDO::getWarehouseCode, reqVO.getWarehouseCode())
                .likeIfPresent(ProductSalseLineDO::getWarehouseName, reqVO.getWarehouseName())
                .eqIfPresent(ProductSalseLineDO::getLocationId, reqVO.getLocationId())
                .eqIfPresent(ProductSalseLineDO::getLocationCode, reqVO.getLocationCode())
                .likeIfPresent(ProductSalseLineDO::getLocationName, reqVO.getLocationName())
                .eqIfPresent(ProductSalseLineDO::getAreaId, reqVO.getAreaId())
                .eqIfPresent(ProductSalseLineDO::getAreaCode, reqVO.getAreaCode())
                .likeIfPresent(ProductSalseLineDO::getAreaName, reqVO.getAreaName())
                .eqIfPresent(ProductSalseLineDO::getRemark, reqVO.getRemark())
                .eqIfPresent(ProductSalseLineDO::getAttr1, reqVO.getAttr1())
                .eqIfPresent(ProductSalseLineDO::getAttr2, reqVO.getAttr2())
                .eqIfPresent(ProductSalseLineDO::getAttr3, reqVO.getAttr3())
                .eqIfPresent(ProductSalseLineDO::getAttr4, reqVO.getAttr4())
                .betweenIfPresent(ProductSalseLineDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(ProductSalseLineDO::getId));
    }

    default List<ProductSalseLineDO> selectList(ProductSalseLineExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<ProductSalseLineDO>()
                .eqIfPresent(ProductSalseLineDO::getSalseId, reqVO.getSalseId())
                .eqIfPresent(ProductSalseLineDO::getMaterialStockId, reqVO.getMaterialStockId())
                .eqIfPresent(ProductSalseLineDO::getItemId, reqVO.getItemId())
                .eqIfPresent(ProductSalseLineDO::getItemCode, reqVO.getItemCode())
                .likeIfPresent(ProductSalseLineDO::getItemName, reqVO.getItemName())
                .eqIfPresent(ProductSalseLineDO::getSpecification, reqVO.getSpecification())
                .eqIfPresent(ProductSalseLineDO::getUnitOfMeasure, reqVO.getUnitOfMeasure())
                .eqIfPresent(ProductSalseLineDO::getQuantitySalse, reqVO.getQuantitySalse())
                .eqIfPresent(ProductSalseLineDO::getBatchCode, reqVO.getBatchCode())
                .eqIfPresent(ProductSalseLineDO::getWarehouseId, reqVO.getWarehouseId())
                .eqIfPresent(ProductSalseLineDO::getWarehouseCode, reqVO.getWarehouseCode())
                .likeIfPresent(ProductSalseLineDO::getWarehouseName, reqVO.getWarehouseName())
                .eqIfPresent(ProductSalseLineDO::getLocationId, reqVO.getLocationId())
                .eqIfPresent(ProductSalseLineDO::getLocationCode, reqVO.getLocationCode())
                .likeIfPresent(ProductSalseLineDO::getLocationName, reqVO.getLocationName())
                .eqIfPresent(ProductSalseLineDO::getAreaId, reqVO.getAreaId())
                .eqIfPresent(ProductSalseLineDO::getAreaCode, reqVO.getAreaCode())
                .likeIfPresent(ProductSalseLineDO::getAreaName, reqVO.getAreaName())
                .eqIfPresent(ProductSalseLineDO::getRemark, reqVO.getRemark())
                .eqIfPresent(ProductSalseLineDO::getAttr1, reqVO.getAttr1())
                .eqIfPresent(ProductSalseLineDO::getAttr2, reqVO.getAttr2())
                .eqIfPresent(ProductSalseLineDO::getAttr3, reqVO.getAttr3())
                .eqIfPresent(ProductSalseLineDO::getAttr4, reqVO.getAttr4())
                .betweenIfPresent(ProductSalseLineDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(ProductSalseLineDO::getId));
    }

}
