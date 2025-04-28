package com.dofast.module.wms.dal.mysql.materialstock;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;
import com.dofast.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.dofast.framework.mybatis.core.mapper.BaseMapperX;
import com.dofast.module.wms.dal.dataobject.materialstock.MaterialStockDO;
import org.apache.ibatis.annotations.Mapper;
import com.dofast.module.wms.controller.admin.materialstock.vo.*;

/**
 * 库存记录 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface MaterialStockMapper extends BaseMapperX<MaterialStockDO> {

    default MaterialStockDO loadMaterialStock(MaterialStockBaseVO baseVO){
        return selectOne(new LambdaQueryWrapperX<MaterialStockDO>()
                .eqIfPresent(MaterialStockDO::getItemId,baseVO.getItemId())
                .eqIfPresent(MaterialStockDO::getUnitOfMeasure,baseVO.getUnitOfMeasure())
                .likeIfPresent(MaterialStockDO::getBatchCode,baseVO.getBatchCode())
                .eqIfPresent(MaterialStockDO::getWarehouseId,baseVO.getWarehouseId())
                .eqIfPresent(MaterialStockDO::getLocationId,baseVO.getLocationId())
                .eqIfPresent(MaterialStockDO::getAreaId,baseVO.getAreaId())
                .eqIfPresent(MaterialStockDO::getVendorId,baseVO.getVendorId())
                .eqIfPresent(MaterialStockDO::getErpBatchCode,baseVO.getErpBatchCode())
                //.eqIfPresent(MaterialStockDO::getWorkorderId,baseVO.getWorkorderId())
                //.eqIfPresent(MaterialStockDO::getWorkorderCode,baseVO.getWorkorderCode())

        );
    }

    default PageResult<MaterialStockDO> selectPage(MaterialStockPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<MaterialStockDO>()
                .eqIfPresent(MaterialStockDO::getItemTypeId, reqVO.getItemTypeId())
                .eqIfPresent(MaterialStockDO::getItemId, reqVO.getItemId())
                .eqIfPresent(MaterialStockDO::getItemCode, reqVO.getItemCode())
                .likeIfPresent(MaterialStockDO::getItemName, reqVO.getItemName())
                .eqIfPresent(MaterialStockDO::getSpecification, reqVO.getSpecification())
                .eqIfPresent(MaterialStockDO::getUnitOfMeasure, reqVO.getUnitOfMeasure())
                .likeIfPresent(MaterialStockDO::getBatchCode, reqVO.getBatchCode())
                .eqIfPresent(MaterialStockDO::getWarehouseId, reqVO.getWarehouseId())
                .eqIfPresent(MaterialStockDO::getWarehouseCode, reqVO.getWarehouseCode())
                .likeIfPresent(MaterialStockDO::getWarehouseName, reqVO.getWarehouseName())
                .eqIfPresent(MaterialStockDO::getLocationId, reqVO.getLocationId())
                .eqIfPresent(MaterialStockDO::getLocationCode, reqVO.getLocationCode())
                .likeIfPresent(MaterialStockDO::getLocationName, reqVO.getLocationName())
                .eqIfPresent(MaterialStockDO::getAreaId, reqVO.getAreaId())
                .eqIfPresent(MaterialStockDO::getAreaCode, reqVO.getAreaCode())
                .likeIfPresent(MaterialStockDO::getAreaName, reqVO.getAreaName())
                .eqIfPresent(MaterialStockDO::getVendorId, reqVO.getVendorId())
                .eqIfPresent(MaterialStockDO::getVendorCode, reqVO.getVendorCode())
                .likeIfPresent(MaterialStockDO::getVendorName, reqVO.getVendorName())
                .eqIfPresent(MaterialStockDO::getVendorNick, reqVO.getVendorNick())
                .gtIfPresent(MaterialStockDO::getQuantityOnhand, 0)
                .eqIfPresent(MaterialStockDO::getWorkorderId, reqVO.getWorkorderId())
                .eqIfPresent(MaterialStockDO::getWorkorderCode, reqVO.getWorkorderCode())
                .betweenIfPresent(MaterialStockDO::getRecptDate, reqVO.getRecptDate())
                .betweenIfPresent(MaterialStockDO::getExpireDate, reqVO.getExpireDate())
                .eqIfPresent(MaterialStockDO::getAttr1, reqVO.getAttr1())
                .eqIfPresent(MaterialStockDO::getAttr2, reqVO.getAttr2())
                .eqIfPresent(MaterialStockDO::getAttr3, reqVO.getAttr3())
                .eqIfPresent(MaterialStockDO::getAttr4, reqVO.getAttr4())
                .eqIfPresent(MaterialStockDO::getRecptStatus, reqVO.getRecptStatus())
                .betweenIfPresent(MaterialStockDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(MaterialStockDO::getErpBatchCode,reqVO.getErpBatchCode())
                .orderByDesc(MaterialStockDO::getId));
    }

    default List<MaterialStockDO> selectList(MaterialStockExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<MaterialStockDO>()
                .eqIfPresent(MaterialStockDO::getItemTypeId, reqVO.getItemTypeId())
                .eqIfPresent(MaterialStockDO::getItemId, reqVO.getItemId())
                .eqIfPresent(MaterialStockDO::getItemCode, reqVO.getItemCode())
                .likeIfPresent(MaterialStockDO::getItemName, reqVO.getItemName())
                .eqIfPresent(MaterialStockDO::getSpecification, reqVO.getSpecification())
                .eqIfPresent(MaterialStockDO::getUnitOfMeasure, reqVO.getUnitOfMeasure())
                .likeIfPresent(MaterialStockDO::getBatchCode, reqVO.getBatchCode())
                .eqIfPresent(MaterialStockDO::getWarehouseId, reqVO.getWarehouseId())
                .eqIfPresent(MaterialStockDO::getWarehouseCode, reqVO.getWarehouseCode())
                .likeIfPresent(MaterialStockDO::getWarehouseName, reqVO.getWarehouseName())
                .eqIfPresent(MaterialStockDO::getLocationId, reqVO.getLocationId())
                .eqIfPresent(MaterialStockDO::getLocationCode, reqVO.getLocationCode())
                .likeIfPresent(MaterialStockDO::getLocationName, reqVO.getLocationName())
                .eqIfPresent(MaterialStockDO::getAreaId, reqVO.getAreaId())
                .eqIfPresent(MaterialStockDO::getAreaCode, reqVO.getAreaCode())
                .likeIfPresent(MaterialStockDO::getAreaName, reqVO.getAreaName())
                .eqIfPresent(MaterialStockDO::getVendorId, reqVO.getVendorId())
                .eqIfPresent(MaterialStockDO::getVendorCode, reqVO.getVendorCode())
                .likeIfPresent(MaterialStockDO::getVendorName, reqVO.getVendorName())
                .eqIfPresent(MaterialStockDO::getVendorNick, reqVO.getVendorNick())
                //.eqIfPresent(MaterialStockDO::getQuantityOnhand, reqVO.getQuantityOnhand())
                .gtIfPresent(MaterialStockDO::getQuantityOnhand, 0)
                .eqIfPresent(MaterialStockDO::getWorkorderId, reqVO.getWorkorderId())
                .eqIfPresent(MaterialStockDO::getWorkorderCode, reqVO.getWorkorderCode())
                .betweenIfPresent(MaterialStockDO::getRecptDate, reqVO.getRecptDate())
                .betweenIfPresent(MaterialStockDO::getExpireDate, reqVO.getExpireDate())
                .eqIfPresent(MaterialStockDO::getAttr1, reqVO.getAttr1())
                .eqIfPresent(MaterialStockDO::getAttr2, reqVO.getAttr2())
                .eqIfPresent(MaterialStockDO::getAttr3, reqVO.getAttr3())
                .eqIfPresent(MaterialStockDO::getAttr4, reqVO.getAttr4())
                .eqIfPresent(MaterialStockDO::getRecptStatus, reqVO.getRecptStatus())
                .betweenIfPresent(MaterialStockDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(MaterialStockDO::getErpBatchCode,reqVO.getErpBatchCode())
                .orderByDesc(MaterialStockDO::getId));
    }

}
