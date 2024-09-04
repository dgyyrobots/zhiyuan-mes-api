package com.dofast.module.iot.dal.mysql.alert;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;
import com.dofast.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.dofast.framework.mybatis.core.mapper.BaseMapperX;
import com.dofast.module.iot.dal.dataobject.alert.AlertDO;
import org.apache.ibatis.annotations.Mapper;
import com.dofast.module.iot.controller.admin.alert.vo.*;

/**
 * 设备告警 Mapper
 *
 * @author 惠智造
 */
@Mapper
public interface AlertMapper extends BaseMapperX<AlertDO> {

    default PageResult<AlertDO> selectPage(AlertPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<AlertDO>()
                .likeIfPresent(AlertDO::getAlertName, reqVO.getAlertName())
                .eqIfPresent(AlertDO::getAlertLevel, reqVO.getAlertLevel())
                .eqIfPresent(AlertDO::getProductId, reqVO.getProductId())
                .likeIfPresent(AlertDO::getProductName, reqVO.getProductName())
                .eqIfPresent(AlertDO::getTriggers, reqVO.getTriggers())
                .eqIfPresent(AlertDO::getActions, reqVO.getActions())
                .eqIfPresent(AlertDO::getStatus, reqVO.getStatus())
                .eqIfPresent(AlertDO::getRemark, reqVO.getRemark())
                .betweenIfPresent(AlertDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(AlertDO::getId));
    }

    default List<AlertDO> selectList(AlertExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<AlertDO>()
                .likeIfPresent(AlertDO::getAlertName, reqVO.getAlertName())
                .eqIfPresent(AlertDO::getAlertLevel, reqVO.getAlertLevel())
                .eqIfPresent(AlertDO::getProductId, reqVO.getProductId())
                .likeIfPresent(AlertDO::getProductName, reqVO.getProductName())
                .eqIfPresent(AlertDO::getTriggers, reqVO.getTriggers())
                .eqIfPresent(AlertDO::getActions, reqVO.getActions())
                .eqIfPresent(AlertDO::getStatus, reqVO.getStatus())
                .eqIfPresent(AlertDO::getRemark, reqVO.getRemark())
                .betweenIfPresent(AlertDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(AlertDO::getId));
    }

}
