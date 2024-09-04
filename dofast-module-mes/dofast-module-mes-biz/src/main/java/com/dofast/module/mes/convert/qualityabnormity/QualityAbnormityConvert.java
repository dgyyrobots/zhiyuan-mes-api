package com.dofast.module.mes.convert.qualityabnormity;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.dofast.module.mes.controller.admin.qualityabnormity.vo.*;
import com.dofast.module.mes.dal.dataobject.qualityabnormity.QualityAbnormityDO;

/**
 * 品质异常信息 Convert
 *
 * @author 惠智造
 */
@Mapper
public interface QualityAbnormityConvert {

    QualityAbnormityConvert INSTANCE = Mappers.getMapper(QualityAbnormityConvert.class);

    QualityAbnormityDO convert(QualityAbnormityCreateReqVO bean);

    QualityAbnormityDO convert(QualityAbnormityUpdateReqVO bean);

    QualityAbnormityRespVO convert(QualityAbnormityDO bean);

    List<QualityAbnormityRespVO> convertList(List<QualityAbnormityDO> list);

    PageResult<QualityAbnormityRespVO> convertPage(PageResult<QualityAbnormityDO> page);

    List<QualityAbnormityExcelVO> convertList02(List<QualityAbnormityDO> list);

}
