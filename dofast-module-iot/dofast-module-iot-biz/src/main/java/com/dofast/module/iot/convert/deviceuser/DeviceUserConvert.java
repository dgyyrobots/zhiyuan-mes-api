package com.dofast.module.iot.convert.deviceuser;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.dofast.module.iot.controller.admin.deviceuser.vo.*;
import com.dofast.module.iot.dal.dataobject.deviceuser.DeviceUserDO;

/**
 * 设备用户 Convert
 *
 * @author 惠智造
 */
@Mapper
public interface DeviceUserConvert {

    DeviceUserConvert INSTANCE = Mappers.getMapper(DeviceUserConvert.class);

    DeviceUserDO convert(DeviceUserCreateReqVO bean);

    DeviceUserDO convert(DeviceUserUpdateReqVO bean);

    DeviceUserRespVO convert(DeviceUserDO bean);

    List<DeviceUserRespVO> convertList(List<DeviceUserDO> list);

    PageResult<DeviceUserRespVO> convertPage(PageResult<DeviceUserDO> page);

    List<DeviceUserExcelVO> convertList02(List<DeviceUserDO> list);

}
