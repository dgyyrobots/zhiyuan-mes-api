package com.dofast.module.iot.convert.devicejob;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.dofast.module.iot.controller.admin.devicejob.vo.*;
import com.dofast.module.iot.dal.dataobject.devicejob.DeviceJobDO;

/**
 * 设备定时 Convert
 *
 * @author 惠智造
 */
@Mapper
public interface DeviceJobConvert {

    DeviceJobConvert INSTANCE = Mappers.getMapper(DeviceJobConvert.class);

    DeviceJobDO convert(DeviceJobCreateReqVO bean);

    DeviceJobDO convert(DeviceJobUpdateReqVO bean);

    DeviceJobRespVO convert(DeviceJobDO bean);

    List<DeviceJobRespVO> convertList(List<DeviceJobDO> list);

    PageResult<DeviceJobRespVO> convertPage(PageResult<DeviceJobDO> page);

    List<DeviceJobExcelVO> convertList02(List<DeviceJobDO> list);

}
