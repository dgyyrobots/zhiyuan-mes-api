package com.dofast.module.iot.dal.mysql.device;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;
import com.dofast.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.dofast.framework.mybatis.core.mapper.BaseMapperX;
import com.dofast.module.iot.dal.dataobject.device.DeviceDO;
import org.apache.ibatis.annotations.Mapper;
import com.dofast.module.iot.controller.admin.device.vo.*;

/**
 * 设备 Mapper
 *
 * @author 惠智造
 */
@Mapper
public interface DeviceMapper extends BaseMapperX<DeviceDO> {

    default PageResult<DeviceDO> selectPage(DevicePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<DeviceDO>()
                .likeIfPresent(DeviceDO::getDeviceName, reqVO.getDeviceName())
                .eqIfPresent(DeviceDO::getProductId, reqVO.getProductId())
                .likeIfPresent(DeviceDO::getProductName, reqVO.getProductName())
                .eqIfPresent(DeviceDO::getUserId, reqVO.getUserId())
                .likeIfPresent(DeviceDO::getUserName, reqVO.getUserName())
                .eqIfPresent(DeviceDO::getSerialNumber, reqVO.getSerialNumber())
                .eqIfPresent(DeviceDO::getFirmwareVersion, reqVO.getFirmwareVersion())
                .eqIfPresent(DeviceDO::getStatus, reqVO.getStatus())
                .eqIfPresent(DeviceDO::getRssi, reqVO.getRssi())
                .eqIfPresent(DeviceDO::getIsShadow, reqVO.getIsShadow())
                .eqIfPresent(DeviceDO::getLocationWay, reqVO.getLocationWay())
                .eqIfPresent(DeviceDO::getThingsModelValue, reqVO.getThingsModelValue())
                .eqIfPresent(DeviceDO::getNetworkAddress, reqVO.getNetworkAddress())
                .eqIfPresent(DeviceDO::getNetworkIp, reqVO.getNetworkIp())
                .eqIfPresent(DeviceDO::getLongitude, reqVO.getLongitude())
                .eqIfPresent(DeviceDO::getLatitude, reqVO.getLatitude())
                .betweenIfPresent(DeviceDO::getActiveTime, reqVO.getActiveTime())
                .eqIfPresent(DeviceDO::getSummary, reqVO.getSummary())
                .eqIfPresent(DeviceDO::getImgUrl, reqVO.getImgUrl())
                .eqIfPresent(DeviceDO::getRemark, reqVO.getRemark())
                .betweenIfPresent(DeviceDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(DeviceDO::getId));
    }

    default List<DeviceDO> selectList(DeviceExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<DeviceDO>()
                .likeIfPresent(DeviceDO::getDeviceName, reqVO.getDeviceName())
                .eqIfPresent(DeviceDO::getProductId, reqVO.getProductId())
                .likeIfPresent(DeviceDO::getProductName, reqVO.getProductName())
                .eqIfPresent(DeviceDO::getUserId, reqVO.getUserId())
                .likeIfPresent(DeviceDO::getUserName, reqVO.getUserName())
                .eqIfPresent(DeviceDO::getSerialNumber, reqVO.getSerialNumber())
                .eqIfPresent(DeviceDO::getFirmwareVersion, reqVO.getFirmwareVersion())
                .eqIfPresent(DeviceDO::getStatus, reqVO.getStatus())
                .eqIfPresent(DeviceDO::getRssi, reqVO.getRssi())
                .eqIfPresent(DeviceDO::getIsShadow, reqVO.getIsShadow())
                .eqIfPresent(DeviceDO::getLocationWay, reqVO.getLocationWay())
                .eqIfPresent(DeviceDO::getThingsModelValue, reqVO.getThingsModelValue())
                .eqIfPresent(DeviceDO::getNetworkAddress, reqVO.getNetworkAddress())
                .eqIfPresent(DeviceDO::getNetworkIp, reqVO.getNetworkIp())
                .eqIfPresent(DeviceDO::getLongitude, reqVO.getLongitude())
                .eqIfPresent(DeviceDO::getLatitude, reqVO.getLatitude())
                .betweenIfPresent(DeviceDO::getActiveTime, reqVO.getActiveTime())
                .eqIfPresent(DeviceDO::getSummary, reqVO.getSummary())
                .eqIfPresent(DeviceDO::getImgUrl, reqVO.getImgUrl())
                .eqIfPresent(DeviceDO::getRemark, reqVO.getRemark())
                .betweenIfPresent(DeviceDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(DeviceDO::getId));
    }

}
