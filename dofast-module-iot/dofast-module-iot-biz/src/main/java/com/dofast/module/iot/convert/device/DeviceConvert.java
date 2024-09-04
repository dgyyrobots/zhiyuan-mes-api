package com.dofast.module.iot.convert.device;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.dofast.module.iot.controller.admin.device.vo.*;
import com.dofast.module.iot.dal.dataobject.device.DeviceDO;

/**
 * 设备 Convert
 *
 * @author 惠智造
 */
@Mapper
public interface DeviceConvert {

    DeviceConvert INSTANCE = Mappers.getMapper(DeviceConvert.class);

    DeviceDO convert(DeviceCreateReqVO bean);

    DeviceDO convert(DeviceUpdateReqVO bean);

    DeviceRespVO convert(DeviceDO bean);

    List<DeviceRespVO> convertList(List<DeviceDO> list);

    PageResult<DeviceRespVO> convertPage(PageResult<DeviceDO> page);

    List<DeviceExcelVO> convertList02(List<DeviceDO> list);

}
