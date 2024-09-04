package com.dofast.module.iot.dal.mysql.devicejob;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;
import com.dofast.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.dofast.framework.mybatis.core.mapper.BaseMapperX;
import com.dofast.module.iot.dal.dataobject.devicejob.DeviceJobDO;
import org.apache.ibatis.annotations.Mapper;
import com.dofast.module.iot.controller.admin.devicejob.vo.*;

/**
 * 设备定时 Mapper
 *
 * @author 惠智造
 */
@Mapper
public interface DeviceJobMapper extends BaseMapperX<DeviceJobDO> {

    default PageResult<DeviceJobDO> selectPage(DeviceJobPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<DeviceJobDO>()
                .eqIfPresent(DeviceJobDO::getCronExpression, reqVO.getCronExpression())
                .eqIfPresent(DeviceJobDO::getMisfirePolicy, reqVO.getMisfirePolicy())
                .eqIfPresent(DeviceJobDO::getConcurrent, reqVO.getConcurrent())
                .eqIfPresent(DeviceJobDO::getStatus, reqVO.getStatus())
                .eqIfPresent(DeviceJobDO::getRemark, reqVO.getRemark())
                .eqIfPresent(DeviceJobDO::getDeviceId, reqVO.getDeviceId())
                .eqIfPresent(DeviceJobDO::getSerialNumber, reqVO.getSerialNumber())
                .likeIfPresent(DeviceJobDO::getDeviceName, reqVO.getDeviceName())
                .eqIfPresent(DeviceJobDO::getIsAdvance, reqVO.getIsAdvance())
                .eqIfPresent(DeviceJobDO::getActions, reqVO.getActions())
                .eqIfPresent(DeviceJobDO::getJobType, reqVO.getJobType())
                .eqIfPresent(DeviceJobDO::getProductId, reqVO.getProductId())
                .likeIfPresent(DeviceJobDO::getProductName, reqVO.getProductName())
                .eqIfPresent(DeviceJobDO::getSceneId, reqVO.getSceneId())
                .eqIfPresent(DeviceJobDO::getAlertId, reqVO.getAlertId())
                .betweenIfPresent(DeviceJobDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(DeviceJobDO::getId));
    }

    default List<DeviceJobDO> selectList(DeviceJobExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<DeviceJobDO>()
                .eqIfPresent(DeviceJobDO::getCronExpression, reqVO.getCronExpression())
                .eqIfPresent(DeviceJobDO::getMisfirePolicy, reqVO.getMisfirePolicy())
                .eqIfPresent(DeviceJobDO::getConcurrent, reqVO.getConcurrent())
                .eqIfPresent(DeviceJobDO::getStatus, reqVO.getStatus())
                .eqIfPresent(DeviceJobDO::getRemark, reqVO.getRemark())
                .eqIfPresent(DeviceJobDO::getDeviceId, reqVO.getDeviceId())
                .eqIfPresent(DeviceJobDO::getSerialNumber, reqVO.getSerialNumber())
                .likeIfPresent(DeviceJobDO::getDeviceName, reqVO.getDeviceName())
                .eqIfPresent(DeviceJobDO::getIsAdvance, reqVO.getIsAdvance())
                .eqIfPresent(DeviceJobDO::getActions, reqVO.getActions())
                .eqIfPresent(DeviceJobDO::getJobType, reqVO.getJobType())
                .eqIfPresent(DeviceJobDO::getProductId, reqVO.getProductId())
                .likeIfPresent(DeviceJobDO::getProductName, reqVO.getProductName())
                .eqIfPresent(DeviceJobDO::getSceneId, reqVO.getSceneId())
                .eqIfPresent(DeviceJobDO::getAlertId, reqVO.getAlertId())
                .betweenIfPresent(DeviceJobDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(DeviceJobDO::getId));
    }

}
