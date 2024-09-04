package com.dofast.module.iot.convert.alert;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.dofast.module.iot.controller.admin.alert.vo.*;
import com.dofast.module.iot.dal.dataobject.alert.AlertDO;

/**
 * 设备告警 Convert
 *
 * @author 惠智造
 */
@Mapper
public interface AlertConvert {

    AlertConvert INSTANCE = Mappers.getMapper(AlertConvert.class);

    AlertDO convert(AlertCreateReqVO bean);

    AlertDO convert(AlertUpdateReqVO bean);

    AlertRespVO convert(AlertDO bean);

    List<AlertRespVO> convertList(List<AlertDO> list);

    PageResult<AlertRespVO> convertPage(PageResult<AlertDO> page);

    List<AlertExcelVO> convertList02(List<AlertDO> list);

}
