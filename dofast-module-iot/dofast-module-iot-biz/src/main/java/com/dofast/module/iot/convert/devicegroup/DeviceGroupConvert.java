package com.dofast.module.iot.convert.devicegroup;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.dofast.module.iot.controller.admin.devicegroup.vo.*;
import com.dofast.module.iot.dal.dataobject.devicegroup.DeviceGroupDO;

/**
 * 设备分组 Convert
 *
 * @author 惠智造
 */
@Mapper
public interface DeviceGroupConvert {

    DeviceGroupConvert INSTANCE = Mappers.getMapper(DeviceGroupConvert.class);

    DeviceGroupDO convert(DeviceGroupCreateReqVO bean);

    DeviceGroupDO convert(DeviceGroupUpdateReqVO bean);

    DeviceGroupRespVO convert(DeviceGroupDO bean);

    List<DeviceGroupRespVO> convertList(List<DeviceGroupDO> list);

    PageResult<DeviceGroupRespVO> convertPage(PageResult<DeviceGroupDO> page);

    List<DeviceGroupExcelVO> convertList02(List<DeviceGroupDO> list);

}
