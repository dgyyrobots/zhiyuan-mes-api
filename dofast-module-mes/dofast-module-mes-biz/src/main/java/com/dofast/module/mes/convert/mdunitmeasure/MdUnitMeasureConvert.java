package com.dofast.module.mes.convert.mdunitmeasure;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.dofast.module.mes.controller.admin.mdunitmeasure.vo.*;
import com.dofast.module.mes.dal.dataobject.mdunitmeasure.MdUnitMeasureDO;

/**
 * 单位 Convert
 *
 * @author 芋道源码
 */
@Mapper
public interface MdUnitMeasureConvert {

    MdUnitMeasureConvert INSTANCE = Mappers.getMapper(MdUnitMeasureConvert.class);

    MdUnitMeasureDO convert(MdUnitMeasureCreateReqVO bean);

    MdUnitMeasureDO convert(MdUnitMeasureUpdateReqVO bean);

    MdUnitMeasureRespVO convert(MdUnitMeasureDO bean);

    List<MdUnitMeasureRespVO> convertList(List<MdUnitMeasureDO> list);

    PageResult<MdUnitMeasureRespVO> convertPage(PageResult<MdUnitMeasureDO> page);

    List<MdUnitMeasureExcelVO> convertList02(List<MdUnitMeasureDO> list);

}
