package com.dofast.module.iot.dal.mysql.devicelog;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;
import com.dofast.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.dofast.framework.mybatis.core.mapper.BaseMapperX;
import com.dofast.module.iot.dal.dataobject.devicelog.DeviceLogDO;
import org.apache.ibatis.annotations.Mapper;
import com.dofast.module.iot.controller.admin.devicelog.vo.*;

/**
 * 设备日志 Mapper
 *
 * @author 惠智造
 */
@Mapper
public interface DeviceLogMapper extends BaseMapperX<DeviceLogDO> {

    default PageResult<DeviceLogDO> selectPage(DeviceLogPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<DeviceLogDO>()
                .eqIfPresent(DeviceLogDO::getIdentity, reqVO.getIdentity())
                .eqIfPresent(DeviceLogDO::getLogType, reqVO.getLogType())
                .eqIfPresent(DeviceLogDO::getLogValue, reqVO.getLogValue())
                .eqIfPresent(DeviceLogDO::getDeviceId, reqVO.getDeviceId())
                .likeIfPresent(DeviceLogDO::getDeviceName, reqVO.getDeviceName())
                .eqIfPresent(DeviceLogDO::getSerialNumber, reqVO.getSerialNumber())
                .eqIfPresent(DeviceLogDO::getIsMonitor, reqVO.getIsMonitor())
                .eqIfPresent(DeviceLogDO::getMode, reqVO.getMode())
                .eqIfPresent(DeviceLogDO::getUserId, reqVO.getUserId())
                .likeIfPresent(DeviceLogDO::getUserName, reqVO.getUserName())
                .eqIfPresent(DeviceLogDO::getRemark, reqVO.getRemark())
                .betweenIfPresent(DeviceLogDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(DeviceLogDO::getId));
    }

    default List<DeviceLogDO> selectList(DeviceLogExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<DeviceLogDO>()
                .eqIfPresent(DeviceLogDO::getIdentity, reqVO.getIdentity())
                .eqIfPresent(DeviceLogDO::getLogType, reqVO.getLogType())
                .eqIfPresent(DeviceLogDO::getLogValue, reqVO.getLogValue())
                .eqIfPresent(DeviceLogDO::getDeviceId, reqVO.getDeviceId())
                .likeIfPresent(DeviceLogDO::getDeviceName, reqVO.getDeviceName())
                .eqIfPresent(DeviceLogDO::getSerialNumber, reqVO.getSerialNumber())
                .eqIfPresent(DeviceLogDO::getIsMonitor, reqVO.getIsMonitor())
                .eqIfPresent(DeviceLogDO::getMode, reqVO.getMode())
                .eqIfPresent(DeviceLogDO::getUserId, reqVO.getUserId())
                .likeIfPresent(DeviceLogDO::getUserName, reqVO.getUserName())
                .eqIfPresent(DeviceLogDO::getRemark, reqVO.getRemark())
                .betweenIfPresent(DeviceLogDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(DeviceLogDO::getId));
    }

}
