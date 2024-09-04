package com.dofast.module.iot.convert.thingsmodel;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.dofast.module.iot.controller.admin.thingsmodel.vo.*;
import com.dofast.module.iot.dal.dataobject.thingsmodel.ThingsModelDO;

/**
 * 物模型 Convert
 *
 * @author 惠智造
 */
@Mapper
public interface ThingsModelConvert {

    ThingsModelConvert INSTANCE = Mappers.getMapper(ThingsModelConvert.class);

    ThingsModelDO convert(ThingsModelCreateReqVO bean);

    ThingsModelDO convert(ThingsModelUpdateReqVO bean);

    ThingsModelRespVO convert(ThingsModelDO bean);

    List<ThingsModelRespVO> convertList(List<ThingsModelDO> list);

    PageResult<ThingsModelRespVO> convertPage(PageResult<ThingsModelDO> page);

    List<ThingsModelExcelVO> convertList02(List<ThingsModelDO> list);

}
