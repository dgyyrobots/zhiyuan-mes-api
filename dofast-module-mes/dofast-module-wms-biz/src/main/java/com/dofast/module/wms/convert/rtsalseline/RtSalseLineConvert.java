package com.dofast.module.wms.convert.rtsalseline;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.dofast.module.wms.controller.admin.rtsalseline.vo.*;
import com.dofast.module.wms.dal.dataobject.rtsalseline.RtSalseLineDO;

/**
 * 产品销售退货行 Convert
 *
 * @author 芋道源码
 */
@Mapper
public interface RtSalseLineConvert {

    RtSalseLineConvert INSTANCE = Mappers.getMapper(RtSalseLineConvert.class);

    RtSalseLineDO convert(RtSalseLineCreateReqVO bean);

    RtSalseLineDO convert(RtSalseLineUpdateReqVO bean);

    RtSalseLineRespVO convert(RtSalseLineDO bean);

    List<RtSalseLineRespVO> convertList(List<RtSalseLineDO> list);

    PageResult<RtSalseLineRespVO> convertPage(PageResult<RtSalseLineDO> page);

    List<RtSalseLineExcelVO> convertList02(List<RtSalseLineDO> list);

}
