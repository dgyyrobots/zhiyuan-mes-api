package com.dofast.module.wms.dal.mysql.transferline;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;
import com.dofast.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.dofast.framework.mybatis.core.mapper.BaseMapperX;
import com.dofast.module.wms.dal.dataobject.transferline.TransferLineDO;
import org.apache.ibatis.annotations.Mapper;
import com.dofast.module.wms.controller.admin.transferline.vo.*;

/**
 * 转移单行 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface TransferLineMapper extends BaseMapperX<TransferLineDO> {
    default int deleteByTransferId(Long transferId){
        return delete(new LambdaQueryWrapperX<TransferLineDO>().eq(TransferLineDO::getTransferId,transferId));
    }
    default PageResult<TransferLineDO> selectPage(TransferLinePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<TransferLineDO>()
                .eqIfPresent(TransferLineDO::getTransferId, reqVO.getTransferId())
                .eqIfPresent(TransferLineDO::getMaterialStockId, reqVO.getMaterialStockId())
                .eqIfPresent(TransferLineDO::getItemId, reqVO.getItemId())
                .eqIfPresent(TransferLineDO::getItemCode, reqVO.getItemCode())
                .likeIfPresent(TransferLineDO::getItemName, reqVO.getItemName())
                .eqIfPresent(TransferLineDO::getSpecification, reqVO.getSpecification())
                .eqIfPresent(TransferLineDO::getUnitOfMeasure, reqVO.getUnitOfMeasure())
                .eqIfPresent(TransferLineDO::getQuantityTransfer, reqVO.getQuantityTransfer())
                .eqIfPresent(TransferLineDO::getWorkorderId, reqVO.getWorkorderId())
                .eqIfPresent(TransferLineDO::getWorkorderCode, reqVO.getWorkorderCode())
                .eqIfPresent(TransferLineDO::getBatchCode, reqVO.getBatchCode())
                .eqIfPresent(TransferLineDO::getFromWarehouseId, reqVO.getFromWarehouseId())
                .eqIfPresent(TransferLineDO::getFromWarehouseCode, reqVO.getFromWarehouseCode())
                .likeIfPresent(TransferLineDO::getFromWarehouseName, reqVO.getFromWarehouseName())
                .eqIfPresent(TransferLineDO::getFromLocationId, reqVO.getFromLocationId())
                .eqIfPresent(TransferLineDO::getFromLocationCode, reqVO.getFromLocationCode())
                .likeIfPresent(TransferLineDO::getFromLocationName, reqVO.getFromLocationName())
                .eqIfPresent(TransferLineDO::getFromAreaId, reqVO.getFromAreaId())
                .eqIfPresent(TransferLineDO::getFromAreaCode, reqVO.getFromAreaCode())
                .likeIfPresent(TransferLineDO::getFromAreaName, reqVO.getFromAreaName())
                .eqIfPresent(TransferLineDO::getToWarehouseId, reqVO.getToWarehouseId())
                .eqIfPresent(TransferLineDO::getToWarehouseCode, reqVO.getToWarehouseCode())
                .likeIfPresent(TransferLineDO::getToWarehouseName, reqVO.getToWarehouseName())
                .eqIfPresent(TransferLineDO::getToLocationId, reqVO.getToLocationId())
                .eqIfPresent(TransferLineDO::getToLocationCode, reqVO.getToLocationCode())
                .likeIfPresent(TransferLineDO::getToLocationName, reqVO.getToLocationName())
                .eqIfPresent(TransferLineDO::getToAreaId, reqVO.getToAreaId())
                .eqIfPresent(TransferLineDO::getToAreaCode, reqVO.getToAreaCode())
                .likeIfPresent(TransferLineDO::getToAreaName, reqVO.getToAreaName())
                .betweenIfPresent(TransferLineDO::getExpireDate, reqVO.getExpireDate())
                .eqIfPresent(TransferLineDO::getVendorId, reqVO.getVendorId())
                .eqIfPresent(TransferLineDO::getVendorCode, reqVO.getVendorCode())
                .likeIfPresent(TransferLineDO::getVendorName, reqVO.getVendorName())
                .eqIfPresent(TransferLineDO::getVendorNick, reqVO.getVendorNick())
                .eqIfPresent(TransferLineDO::getRemark, reqVO.getRemark())
                .eqIfPresent(TransferLineDO::getAttr1, reqVO.getAttr1())
                .eqIfPresent(TransferLineDO::getAttr2, reqVO.getAttr2())
                .eqIfPresent(TransferLineDO::getAttr3, reqVO.getAttr3())
                .eqIfPresent(TransferLineDO::getAttr4, reqVO.getAttr4())
                .betweenIfPresent(TransferLineDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(TransferLineDO::getId));
    }

    default List<TransferLineDO> selectList(TransferLineListVO reqVO) {
        return selectList(new LambdaQueryWrapperX<TransferLineDO>()
                .eqIfPresent(TransferLineDO::getTransferId, reqVO.getTransferId())
                .eqIfPresent(TransferLineDO::getMaterialStockId, reqVO.getMaterialStockId())
                .eqIfPresent(TransferLineDO::getItemId, reqVO.getItemId())
                .eqIfPresent(TransferLineDO::getItemCode, reqVO.getItemCode())
                .likeIfPresent(TransferLineDO::getItemName, reqVO.getItemName())
                .eqIfPresent(TransferLineDO::getSpecification, reqVO.getSpecification())
                .eqIfPresent(TransferLineDO::getUnitOfMeasure, reqVO.getUnitOfMeasure())
                .eqIfPresent(TransferLineDO::getQuantityTransfer, reqVO.getQuantityTransfer())
                .eqIfPresent(TransferLineDO::getWorkorderId, reqVO.getWorkorderId())
                .eqIfPresent(TransferLineDO::getWorkorderCode, reqVO.getWorkorderCode())
                .eqIfPresent(TransferLineDO::getBatchCode, reqVO.getBatchCode())
                .eqIfPresent(TransferLineDO::getFromWarehouseId, reqVO.getFromWarehouseId())
                .eqIfPresent(TransferLineDO::getFromWarehouseCode, reqVO.getFromWarehouseCode())
                .likeIfPresent(TransferLineDO::getFromWarehouseName, reqVO.getFromWarehouseName())
                .eqIfPresent(TransferLineDO::getFromLocationId, reqVO.getFromLocationId())
                .eqIfPresent(TransferLineDO::getFromLocationCode, reqVO.getFromLocationCode())
                .likeIfPresent(TransferLineDO::getFromLocationName, reqVO.getFromLocationName())
                .eqIfPresent(TransferLineDO::getFromAreaId, reqVO.getFromAreaId())
                .eqIfPresent(TransferLineDO::getFromAreaCode, reqVO.getFromAreaCode())
                .likeIfPresent(TransferLineDO::getFromAreaName, reqVO.getFromAreaName())
                .eqIfPresent(TransferLineDO::getToWarehouseId, reqVO.getToWarehouseId())
                .eqIfPresent(TransferLineDO::getToWarehouseCode, reqVO.getToWarehouseCode())
                .likeIfPresent(TransferLineDO::getToWarehouseName, reqVO.getToWarehouseName())
                .eqIfPresent(TransferLineDO::getToLocationId, reqVO.getToLocationId())
                .eqIfPresent(TransferLineDO::getToLocationCode, reqVO.getToLocationCode())
                .likeIfPresent(TransferLineDO::getToLocationName, reqVO.getToLocationName())
                .eqIfPresent(TransferLineDO::getToAreaId, reqVO.getToAreaId())
                .eqIfPresent(TransferLineDO::getToAreaCode, reqVO.getToAreaCode())
                .likeIfPresent(TransferLineDO::getToAreaName, reqVO.getToAreaName())
                .eq(TransferLineDO::getExpireDate, reqVO.getExpireDate())
                .eqIfPresent(TransferLineDO::getVendorId, reqVO.getVendorId())
                .eqIfPresent(TransferLineDO::getVendorCode, reqVO.getVendorCode())
                .likeIfPresent(TransferLineDO::getVendorName, reqVO.getVendorName())
                .eqIfPresent(TransferLineDO::getVendorNick, reqVO.getVendorNick())
                .eqIfPresent(TransferLineDO::getRemark, reqVO.getRemark())
                .eqIfPresent(TransferLineDO::getAttr1, reqVO.getAttr1())
                .eqIfPresent(TransferLineDO::getAttr2, reqVO.getAttr2())
                .eqIfPresent(TransferLineDO::getAttr3, reqVO.getAttr3())
                .eqIfPresent(TransferLineDO::getAttr4, reqVO.getAttr4())
                .orderByDesc(TransferLineDO::getId));
    }


    default List<TransferLineDO> selectList(TransferLineExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<TransferLineDO>()
                .eqIfPresent(TransferLineDO::getTransferId, reqVO.getTransferId())
                .eqIfPresent(TransferLineDO::getMaterialStockId, reqVO.getMaterialStockId())
                .eqIfPresent(TransferLineDO::getItemId, reqVO.getItemId())
                .eqIfPresent(TransferLineDO::getItemCode, reqVO.getItemCode())
                .likeIfPresent(TransferLineDO::getItemName, reqVO.getItemName())
                .eqIfPresent(TransferLineDO::getSpecification, reqVO.getSpecification())
                .eqIfPresent(TransferLineDO::getUnitOfMeasure, reqVO.getUnitOfMeasure())
                .eqIfPresent(TransferLineDO::getQuantityTransfer, reqVO.getQuantityTransfer())
                .eqIfPresent(TransferLineDO::getWorkorderId, reqVO.getWorkorderId())
                .eqIfPresent(TransferLineDO::getWorkorderCode, reqVO.getWorkorderCode())
                .eqIfPresent(TransferLineDO::getBatchCode, reqVO.getBatchCode())
                .eqIfPresent(TransferLineDO::getFromWarehouseId, reqVO.getFromWarehouseId())
                .eqIfPresent(TransferLineDO::getFromWarehouseCode, reqVO.getFromWarehouseCode())
                .likeIfPresent(TransferLineDO::getFromWarehouseName, reqVO.getFromWarehouseName())
                .eqIfPresent(TransferLineDO::getFromLocationId, reqVO.getFromLocationId())
                .eqIfPresent(TransferLineDO::getFromLocationCode, reqVO.getFromLocationCode())
                .likeIfPresent(TransferLineDO::getFromLocationName, reqVO.getFromLocationName())
                .eqIfPresent(TransferLineDO::getFromAreaId, reqVO.getFromAreaId())
                .eqIfPresent(TransferLineDO::getFromAreaCode, reqVO.getFromAreaCode())
                .likeIfPresent(TransferLineDO::getFromAreaName, reqVO.getFromAreaName())
                .eqIfPresent(TransferLineDO::getToWarehouseId, reqVO.getToWarehouseId())
                .eqIfPresent(TransferLineDO::getToWarehouseCode, reqVO.getToWarehouseCode())
                .likeIfPresent(TransferLineDO::getToWarehouseName, reqVO.getToWarehouseName())
                .eqIfPresent(TransferLineDO::getToLocationId, reqVO.getToLocationId())
                .eqIfPresent(TransferLineDO::getToLocationCode, reqVO.getToLocationCode())
                .likeIfPresent(TransferLineDO::getToLocationName, reqVO.getToLocationName())
                .eqIfPresent(TransferLineDO::getToAreaId, reqVO.getToAreaId())
                .eqIfPresent(TransferLineDO::getToAreaCode, reqVO.getToAreaCode())
                .likeIfPresent(TransferLineDO::getToAreaName, reqVO.getToAreaName())
                .betweenIfPresent(TransferLineDO::getExpireDate, reqVO.getExpireDate())
                .eqIfPresent(TransferLineDO::getVendorId, reqVO.getVendorId())
                .eqIfPresent(TransferLineDO::getVendorCode, reqVO.getVendorCode())
                .likeIfPresent(TransferLineDO::getVendorName, reqVO.getVendorName())
                .eqIfPresent(TransferLineDO::getVendorNick, reqVO.getVendorNick())
                .eqIfPresent(TransferLineDO::getRemark, reqVO.getRemark())
                .eqIfPresent(TransferLineDO::getAttr1, reqVO.getAttr1())
                .eqIfPresent(TransferLineDO::getAttr2, reqVO.getAttr2())
                .eqIfPresent(TransferLineDO::getAttr3, reqVO.getAttr3())
                .eqIfPresent(TransferLineDO::getAttr4, reqVO.getAttr4())
                .betweenIfPresent(TransferLineDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(TransferLineDO::getId));
    }

}
