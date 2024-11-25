package com.dofast.module.wms.dal.mysql.feedline;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;
import com.dofast.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.dofast.framework.mybatis.core.mapper.BaseMapperX;
import com.dofast.module.wms.dal.dataobject.feedline.FeedLineDO;
import org.apache.ibatis.annotations.Mapper;
import com.dofast.module.wms.controller.admin.feedline.vo.*;

/**
 * 上料详情 Mapper
 *
 * @author 惠智造
 */
@Mapper
public interface FeedLineMapper extends BaseMapperX<FeedLineDO> {

    default PageResult<FeedLineDO> selectPage(FeedLinePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<FeedLineDO>()
                .eqIfPresent(FeedLineDO::getIssueId, reqVO.getIssueId())
                .eqIfPresent(FeedLineDO::getMaterialStockId, reqVO.getMaterialStockId())
                .eqIfPresent(FeedLineDO::getItemId, reqVO.getItemId())
                .eqIfPresent(FeedLineDO::getItemCode, reqVO.getItemCode())
                .likeIfPresent(FeedLineDO::getItemName, reqVO.getItemName())
                .eqIfPresent(FeedLineDO::getSpecification, reqVO.getSpecification())
                .eqIfPresent(FeedLineDO::getUnitOfMeasure, reqVO.getUnitOfMeasure())
                .eqIfPresent(FeedLineDO::getQuantity, reqVO.getQuantity())
                .eqIfPresent(FeedLineDO::getBatchCode, reqVO.getBatchCode())
                .eqIfPresent(FeedLineDO::getWarehouseId, reqVO.getWarehouseId())
                .eqIfPresent(FeedLineDO::getWarehouseCode, reqVO.getWarehouseCode())
                .likeIfPresent(FeedLineDO::getWarehouseName, reqVO.getWarehouseName())
                .eqIfPresent(FeedLineDO::getLocationId, reqVO.getLocationId())
                .eqIfPresent(FeedLineDO::getLocationCode, reqVO.getLocationCode())
                .likeIfPresent(FeedLineDO::getLocationName, reqVO.getLocationName())
                .eqIfPresent(FeedLineDO::getAreaId, reqVO.getAreaId())
                .eqIfPresent(FeedLineDO::getAreaCode, reqVO.getAreaCode())
                .likeIfPresent(FeedLineDO::getAreaName, reqVO.getAreaName())
                .eqIfPresent(FeedLineDO::getRemark, reqVO.getRemark())
                .eqIfPresent(FeedLineDO::getAttr1, reqVO.getAttr1())
                .eqIfPresent(FeedLineDO::getAttr2, reqVO.getAttr2())
                .eqIfPresent(FeedLineDO::getAttr3, reqVO.getAttr3())
                .eqIfPresent(FeedLineDO::getAttr4, reqVO.getAttr4())
                .betweenIfPresent(FeedLineDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(FeedLineDO::getWorkorderCode, reqVO.getWorkorderCode())
                .eqIfPresent(FeedLineDO::getTaskCode, reqVO.getTaskCode())
                .likeIfPresent(FeedLineDO::getTaskName, reqVO.getTaskName())
                .eqIfPresent(FeedLineDO::getWorkstationCode, reqVO.getWorkstationCode())
                .likeIfPresent(FeedLineDO::getWorkstationName, reqVO.getWorkstationName())
                .eqIfPresent(FeedLineDO::getStatus, reqVO.getStatus())
                .orderByDesc(FeedLineDO::getId));
    }

    default List<FeedLineDO> selectList(FeedLineExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<FeedLineDO>()
                .eqIfPresent(FeedLineDO::getIssueId, reqVO.getIssueId())
                .eqIfPresent(FeedLineDO::getMaterialStockId, reqVO.getMaterialStockId())
                .eqIfPresent(FeedLineDO::getItemId, reqVO.getItemId())
                .eqIfPresent(FeedLineDO::getItemCode, reqVO.getItemCode())
                .likeIfPresent(FeedLineDO::getItemName, reqVO.getItemName())
                .eqIfPresent(FeedLineDO::getSpecification, reqVO.getSpecification())
                .eqIfPresent(FeedLineDO::getUnitOfMeasure, reqVO.getUnitOfMeasure())
                .eqIfPresent(FeedLineDO::getQuantity, reqVO.getQuantity())
                .eqIfPresent(FeedLineDO::getBatchCode, reqVO.getBatchCode())
                .eqIfPresent(FeedLineDO::getWarehouseId, reqVO.getWarehouseId())
                .eqIfPresent(FeedLineDO::getWarehouseCode, reqVO.getWarehouseCode())
                .likeIfPresent(FeedLineDO::getWarehouseName, reqVO.getWarehouseName())
                .eqIfPresent(FeedLineDO::getLocationId, reqVO.getLocationId())
                .eqIfPresent(FeedLineDO::getLocationCode, reqVO.getLocationCode())
                .likeIfPresent(FeedLineDO::getLocationName, reqVO.getLocationName())
                .eqIfPresent(FeedLineDO::getAreaId, reqVO.getAreaId())
                .eqIfPresent(FeedLineDO::getAreaCode, reqVO.getAreaCode())
                .likeIfPresent(FeedLineDO::getAreaName, reqVO.getAreaName())
                .eqIfPresent(FeedLineDO::getRemark, reqVO.getRemark())
                .eqIfPresent(FeedLineDO::getAttr1, reqVO.getAttr1())
                .eqIfPresent(FeedLineDO::getAttr2, reqVO.getAttr2())
                .eqIfPresent(FeedLineDO::getAttr3, reqVO.getAttr3())
                .eqIfPresent(FeedLineDO::getAttr4, reqVO.getAttr4())
                .betweenIfPresent(FeedLineDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(FeedLineDO::getWorkorderCode, reqVO.getWorkorderCode())
                .eqIfPresent(FeedLineDO::getTaskCode, reqVO.getTaskCode())
                .likeIfPresent(FeedLineDO::getTaskName, reqVO.getTaskName())
                .eqIfPresent(FeedLineDO::getWorkstationCode, reqVO.getWorkstationCode())
                .likeIfPresent(FeedLineDO::getWorkstationName, reqVO.getWorkstationName())
                .eqIfPresent(FeedLineDO::getStatus, reqVO.getStatus())
                .orderByDesc(FeedLineDO::getId));
    }

}
