package com.dofast.module.wms.convert.electroformline;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.dofast.module.wms.controller.admin.electroformline.vo.*;
import com.dofast.module.wms.dal.dataobject.electroformline.ElectroformLineDO;

/**
 * 制版房领料单身体 Convert
 *
 * @author 惠智造
 */
@Mapper
public interface ElectroformLineConvert {

    ElectroformLineConvert INSTANCE = Mappers.getMapper(ElectroformLineConvert.class);

    ElectroformLineDO convert(ElectroformLineCreateReqVO bean);

    ElectroformLineDO convert(ElectroformLineUpdateReqVO bean);

    ElectroformLineRespVO convert(ElectroformLineDO bean);

    List<ElectroformLineRespVO> convertList(List<ElectroformLineDO> list);

    PageResult<ElectroformLineRespVO> convertPage(PageResult<ElectroformLineDO> page);

    List<ElectroformLineExcelVO> convertList02(List<ElectroformLineDO> list);

}
