package com.dofast.module.iot.dal.mysql.alertlog;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;
import com.dofast.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.dofast.framework.mybatis.core.mapper.BaseMapperX;
import com.dofast.module.iot.dal.dataobject.alertlog.AlertLogDO;
import org.apache.ibatis.annotations.Mapper;
import com.dofast.module.iot.controller.admin.alertlog.vo.*;

/**
 * 设备告警日志 Mapper
 *
 * @author 惠智造
 */
@Mapper
public interface AlertLogMapper extends BaseMapperX<AlertLogDO> {

    default PageResult<AlertLogDO> selectPage(AlertLogPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<AlertLogDO>()
                .likeIfPresent(AlertLogDO::getAlertName, reqVO.getAlertName())
                .eqIfPresent(AlertLogDO::getAlertLevel, reqVO.getAlertLevel())
                .eqIfPresent(AlertLogDO::getStatus, reqVO.getStatus())
                .eqIfPresent(AlertLogDO::getProductId, reqVO.getProductId())
                .likeIfPresent(AlertLogDO::getProductName, reqVO.getProductName())
                .eqIfPresent(AlertLogDO::getDeviceId, reqVO.getDeviceId())
                .likeIfPresent(AlertLogDO::getDeviceName, reqVO.getDeviceName())
                .eqIfPresent(AlertLogDO::getUserId, reqVO.getUserId())
                .likeIfPresent(AlertLogDO::getUserName, reqVO.getUserName())
                .eqIfPresent(AlertLogDO::getRemark, reqVO.getRemark())
                .eqIfPresent(AlertLogDO::getType, reqVO.getType())
                .betweenIfPresent(AlertLogDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(AlertLogDO::getId));
    }

    default List<AlertLogDO> selectList(AlertLogExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<AlertLogDO>()
                .likeIfPresent(AlertLogDO::getAlertName, reqVO.getAlertName())
                .eqIfPresent(AlertLogDO::getAlertLevel, reqVO.getAlertLevel())
                .eqIfPresent(AlertLogDO::getStatus, reqVO.getStatus())
                .eqIfPresent(AlertLogDO::getProductId, reqVO.getProductId())
                .likeIfPresent(AlertLogDO::getProductName, reqVO.getProductName())
                .eqIfPresent(AlertLogDO::getDeviceId, reqVO.getDeviceId())
                .likeIfPresent(AlertLogDO::getDeviceName, reqVO.getDeviceName())
                .eqIfPresent(AlertLogDO::getUserId, reqVO.getUserId())
                .likeIfPresent(AlertLogDO::getUserName, reqVO.getUserName())
                .eqIfPresent(AlertLogDO::getRemark, reqVO.getRemark())
                .eqIfPresent(AlertLogDO::getType, reqVO.getType())
                .betweenIfPresent(AlertLogDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(AlertLogDO::getId));
    }

}
