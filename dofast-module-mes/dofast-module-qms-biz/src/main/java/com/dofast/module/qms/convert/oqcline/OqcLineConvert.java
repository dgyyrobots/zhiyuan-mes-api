package com.dofast.module.qms.convert.oqcline;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.dofast.module.qms.controller.admin.oqcline.vo.*;
import com.dofast.module.qms.dal.dataobject.oqcline.OqcLineDO;

/**
 * 出货检验单行 Convert
 *
 * @author 芋道源码
 */
@Mapper
public interface OqcLineConvert {

    OqcLineConvert INSTANCE = Mappers.getMapper(OqcLineConvert.class);

    OqcLineDO convert(OqcLineCreateReqVO bean);

    OqcLineDO convert(OqcLineUpdateReqVO bean);

    OqcLineRespVO convert(OqcLineDO bean);

    List<OqcLineRespVO> convertList(List<OqcLineDO> list);

    PageResult<OqcLineRespVO> convertPage(PageResult<OqcLineDO> page);

    List<OqcLineExcelVO> convertList02(List<OqcLineDO> list);

}
