package com.dofast.module.iot.convert.alertlog;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.dofast.module.iot.controller.admin.alertlog.vo.*;
import com.dofast.module.iot.dal.dataobject.alertlog.AlertLogDO;

/**
 * 设备告警日志 Convert
 *
 * @author 惠智造
 */
@Mapper
public interface AlertLogConvert {

    AlertLogConvert INSTANCE = Mappers.getMapper(AlertLogConvert.class);

    AlertLogDO convert(AlertLogCreateReqVO bean);

    AlertLogDO convert(AlertLogUpdateReqVO bean);

    AlertLogRespVO convert(AlertLogDO bean);

    List<AlertLogRespVO> convertList(List<AlertLogDO> list);

    PageResult<AlertLogRespVO> convertPage(PageResult<AlertLogDO> page);

    List<AlertLogExcelVO> convertList02(List<AlertLogDO> list);

}
