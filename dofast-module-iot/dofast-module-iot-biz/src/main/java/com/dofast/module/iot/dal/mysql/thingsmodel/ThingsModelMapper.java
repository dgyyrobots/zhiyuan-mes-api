package com.dofast.module.iot.dal.mysql.thingsmodel;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;
import com.dofast.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.dofast.framework.mybatis.core.mapper.BaseMapperX;
import com.dofast.module.iot.dal.dataobject.thingsmodel.ThingsModelDO;
import org.apache.ibatis.annotations.Mapper;
import com.dofast.module.iot.controller.admin.thingsmodel.vo.*;

/**
 * 物模型 Mapper
 *
 * @author 惠智造
 */
@Mapper
public interface ThingsModelMapper extends BaseMapperX<ThingsModelDO> {

    default PageResult<ThingsModelDO> selectPage(ThingsModelPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ThingsModelDO>()
                .likeIfPresent(ThingsModelDO::getModelName, reqVO.getModelName())
                .eqIfPresent(ThingsModelDO::getProductId, reqVO.getProductId())
                .likeIfPresent(ThingsModelDO::getProductName, reqVO.getProductName())
                .eqIfPresent(ThingsModelDO::getIdentifier, reqVO.getIdentifier())
                .eqIfPresent(ThingsModelDO::getType, reqVO.getType())
                .eqIfPresent(ThingsModelDO::getDatatype, reqVO.getDatatype())
                .eqIfPresent(ThingsModelDO::getSpecs, reqVO.getSpecs())
                .eqIfPresent(ThingsModelDO::getIsTop, reqVO.getIsTop())
                .eqIfPresent(ThingsModelDO::getIsMonitor, reqVO.getIsMonitor())
                .eqIfPresent(ThingsModelDO::getRemark, reqVO.getRemark())
                .betweenIfPresent(ThingsModelDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(ThingsModelDO::getId));
    }

    default List<ThingsModelDO> selectList(ThingsModelExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<ThingsModelDO>()
                .likeIfPresent(ThingsModelDO::getModelName, reqVO.getModelName())
                .eqIfPresent(ThingsModelDO::getProductId, reqVO.getProductId())
                .likeIfPresent(ThingsModelDO::getProductName, reqVO.getProductName())
                .eqIfPresent(ThingsModelDO::getIdentifier, reqVO.getIdentifier())
                .eqIfPresent(ThingsModelDO::getType, reqVO.getType())
                .eqIfPresent(ThingsModelDO::getDatatype, reqVO.getDatatype())
                .eqIfPresent(ThingsModelDO::getSpecs, reqVO.getSpecs())
                .eqIfPresent(ThingsModelDO::getIsTop, reqVO.getIsTop())
                .eqIfPresent(ThingsModelDO::getIsMonitor, reqVO.getIsMonitor())
                .eqIfPresent(ThingsModelDO::getRemark, reqVO.getRemark())
                .betweenIfPresent(ThingsModelDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(ThingsModelDO::getId));
    }

}
