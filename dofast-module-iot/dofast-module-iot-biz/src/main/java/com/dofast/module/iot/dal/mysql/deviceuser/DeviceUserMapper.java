package com.dofast.module.iot.dal.mysql.deviceuser;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;
import com.dofast.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.dofast.framework.mybatis.core.mapper.BaseMapperX;
import com.dofast.module.iot.dal.dataobject.deviceuser.DeviceUserDO;
import org.apache.ibatis.annotations.Mapper;
import com.dofast.module.iot.controller.admin.deviceuser.vo.*;

/**
 * 设备用户 Mapper
 *
 * @author 惠智造
 */
@Mapper
public interface DeviceUserMapper extends BaseMapperX<DeviceUserDO> {

    default PageResult<DeviceUserDO> selectPage(DeviceUserPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<DeviceUserDO>()
                .likeIfPresent(DeviceUserDO::getDeviceName, reqVO.getDeviceName())
                .eqIfPresent(DeviceUserDO::getPhonenumber, reqVO.getPhonenumber())
                .likeIfPresent(DeviceUserDO::getUserName, reqVO.getUserName())
                .eqIfPresent(DeviceUserDO::getIsOwner, reqVO.getIsOwner())
                .eqIfPresent(DeviceUserDO::getRemark, reqVO.getRemark())
                .betweenIfPresent(DeviceUserDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(DeviceUserDO::getId));
    }

    default List<DeviceUserDO> selectList(DeviceUserExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<DeviceUserDO>()
                .likeIfPresent(DeviceUserDO::getDeviceName, reqVO.getDeviceName())
                .eqIfPresent(DeviceUserDO::getPhonenumber, reqVO.getPhonenumber())
                .likeIfPresent(DeviceUserDO::getUserName, reqVO.getUserName())
                .eqIfPresent(DeviceUserDO::getIsOwner, reqVO.getIsOwner())
                .eqIfPresent(DeviceUserDO::getRemark, reqVO.getRemark())
                .betweenIfPresent(DeviceUserDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(DeviceUserDO::getId));
    }

}
