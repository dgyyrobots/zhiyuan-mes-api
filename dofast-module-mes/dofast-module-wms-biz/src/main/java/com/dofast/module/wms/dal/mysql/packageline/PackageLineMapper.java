package com.dofast.module.wms.dal.mysql.packageline;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;
import com.dofast.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.dofast.framework.mybatis.core.mapper.BaseMapperX;
import com.dofast.module.wms.dal.dataobject.packageline.PackageLineDO;
import org.apache.ibatis.annotations.Mapper;
import com.dofast.module.wms.controller.admin.packageline.vo.*;

/**
 * 装箱明细 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface PackageLineMapper extends BaseMapperX<PackageLineDO> {

    default PageResult<PackageLineDO> selectPage(PackageLinePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<PackageLineDO>()
                .eqIfPresent(PackageLineDO::getPackageId, reqVO.getPackageId())
                .eqIfPresent(PackageLineDO::getMaterialStockId, reqVO.getMaterialStockId())
                .eqIfPresent(PackageLineDO::getItemId, reqVO.getItemId())
                .eqIfPresent(PackageLineDO::getItemCode, reqVO.getItemCode())
                .likeIfPresent(PackageLineDO::getItemName, reqVO.getItemName())
                .eqIfPresent(PackageLineDO::getSpecification, reqVO.getSpecification())
                .eqIfPresent(PackageLineDO::getUnitOfMeasure, reqVO.getUnitOfMeasure())
                .eqIfPresent(PackageLineDO::getQuantityPackage, reqVO.getQuantityPackage())
                .eqIfPresent(PackageLineDO::getWorkorderId, reqVO.getWorkorderId())
                .eqIfPresent(PackageLineDO::getWorkorderCode, reqVO.getWorkorderCode())
                .eqIfPresent(PackageLineDO::getBatchCode, reqVO.getBatchCode())
                .eqIfPresent(PackageLineDO::getWarehouseId, reqVO.getWarehouseId())
                .eqIfPresent(PackageLineDO::getWarehouseCode, reqVO.getWarehouseCode())
                .likeIfPresent(PackageLineDO::getWarehouseName, reqVO.getWarehouseName())
                .eqIfPresent(PackageLineDO::getLocationId, reqVO.getLocationId())
                .eqIfPresent(PackageLineDO::getLocationCode, reqVO.getLocationCode())
                .likeIfPresent(PackageLineDO::getLocationName, reqVO.getLocationName())
                .eqIfPresent(PackageLineDO::getAreaId, reqVO.getAreaId())
                .eqIfPresent(PackageLineDO::getAreaCode, reqVO.getAreaCode())
                .likeIfPresent(PackageLineDO::getAreaName, reqVO.getAreaName())
                .betweenIfPresent(PackageLineDO::getExpireDate, reqVO.getExpireDate())
                .eqIfPresent(PackageLineDO::getRemark, reqVO.getRemark())
                .eqIfPresent(PackageLineDO::getAttr1, reqVO.getAttr1())
                .eqIfPresent(PackageLineDO::getAttr2, reqVO.getAttr2())
                .eqIfPresent(PackageLineDO::getAttr3, reqVO.getAttr3())
                .eqIfPresent(PackageLineDO::getAttr4, reqVO.getAttr4())
                .betweenIfPresent(PackageLineDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(PackageLineDO::getId));
    }

    default List<PackageLineDO> selectList(PackageLineExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<PackageLineDO>()
                .eqIfPresent(PackageLineDO::getPackageId, reqVO.getPackageId())
                .eqIfPresent(PackageLineDO::getMaterialStockId, reqVO.getMaterialStockId())
                .eqIfPresent(PackageLineDO::getItemId, reqVO.getItemId())
                .eqIfPresent(PackageLineDO::getItemCode, reqVO.getItemCode())
                .likeIfPresent(PackageLineDO::getItemName, reqVO.getItemName())
                .eqIfPresent(PackageLineDO::getSpecification, reqVO.getSpecification())
                .eqIfPresent(PackageLineDO::getUnitOfMeasure, reqVO.getUnitOfMeasure())
                .eqIfPresent(PackageLineDO::getQuantityPackage, reqVO.getQuantityPackage())
                .eqIfPresent(PackageLineDO::getWorkorderId, reqVO.getWorkorderId())
                .eqIfPresent(PackageLineDO::getWorkorderCode, reqVO.getWorkorderCode())
                .eqIfPresent(PackageLineDO::getBatchCode, reqVO.getBatchCode())
                .eqIfPresent(PackageLineDO::getWarehouseId, reqVO.getWarehouseId())
                .eqIfPresent(PackageLineDO::getWarehouseCode, reqVO.getWarehouseCode())
                .likeIfPresent(PackageLineDO::getWarehouseName, reqVO.getWarehouseName())
                .eqIfPresent(PackageLineDO::getLocationId, reqVO.getLocationId())
                .eqIfPresent(PackageLineDO::getLocationCode, reqVO.getLocationCode())
                .likeIfPresent(PackageLineDO::getLocationName, reqVO.getLocationName())
                .eqIfPresent(PackageLineDO::getAreaId, reqVO.getAreaId())
                .eqIfPresent(PackageLineDO::getAreaCode, reqVO.getAreaCode())
                .likeIfPresent(PackageLineDO::getAreaName, reqVO.getAreaName())
                .betweenIfPresent(PackageLineDO::getExpireDate, reqVO.getExpireDate())
                .eqIfPresent(PackageLineDO::getRemark, reqVO.getRemark())
                .eqIfPresent(PackageLineDO::getAttr1, reqVO.getAttr1())
                .eqIfPresent(PackageLineDO::getAttr2, reqVO.getAttr2())
                .eqIfPresent(PackageLineDO::getAttr3, reqVO.getAttr3())
                .eqIfPresent(PackageLineDO::getAttr4, reqVO.getAttr4())
                .betweenIfPresent(PackageLineDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(PackageLineDO::getId));
    }

}
