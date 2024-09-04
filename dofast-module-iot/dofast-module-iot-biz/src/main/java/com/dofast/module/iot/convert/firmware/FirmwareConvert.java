package com.dofast.module.iot.convert.firmware;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.dofast.module.iot.controller.admin.firmware.vo.*;
import com.dofast.module.iot.dal.dataobject.firmware.FirmwareDO;

/**
 * 产品固件 Convert
 *
 * @author 惠智造
 */
@Mapper
public interface FirmwareConvert {

    FirmwareConvert INSTANCE = Mappers.getMapper(FirmwareConvert.class);

    FirmwareDO convert(FirmwareCreateReqVO bean);

    FirmwareDO convert(FirmwareUpdateReqVO bean);

    FirmwareRespVO convert(FirmwareDO bean);

    List<FirmwareRespVO> convertList(List<FirmwareDO> list);

    PageResult<FirmwareRespVO> convertPage(PageResult<FirmwareDO> page);

    List<FirmwareExcelVO> convertList02(List<FirmwareDO> list);

}
