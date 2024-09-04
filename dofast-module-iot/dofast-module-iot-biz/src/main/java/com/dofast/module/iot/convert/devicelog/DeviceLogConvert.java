package com.dofast.module.iot.convert.devicelog;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.dofast.module.iot.controller.admin.devicelog.vo.*;
import com.dofast.module.iot.dal.dataobject.devicelog.DeviceLogDO;

/**
 * 设备日志 Convert
 *
 * @author 惠智造
 */
@Mapper
public interface DeviceLogConvert {

    DeviceLogConvert INSTANCE = Mappers.getMapper(DeviceLogConvert.class);

    DeviceLogDO convert(DeviceLogCreateReqVO bean);

    DeviceLogDO convert(DeviceLogUpdateReqVO bean);

    DeviceLogRespVO convert(DeviceLogDO bean);

    List<DeviceLogRespVO> convertList(List<DeviceLogDO> list);

    PageResult<DeviceLogRespVO> convertPage(PageResult<DeviceLogDO> page);

    List<DeviceLogExcelVO> convertList02(List<DeviceLogDO> list);

}
