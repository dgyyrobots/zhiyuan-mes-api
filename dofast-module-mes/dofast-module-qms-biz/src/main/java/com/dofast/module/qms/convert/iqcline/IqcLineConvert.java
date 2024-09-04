package com.dofast.module.qms.convert.iqcline;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.dofast.module.qms.controller.admin.iqcline.vo.*;
import com.dofast.module.qms.dal.dataobject.iqcline.IqcLineDO;

/**
 * 来料检验单行 Convert
 *
 * @author 芋道源码
 */
@Mapper
public interface IqcLineConvert {

    IqcLineConvert INSTANCE = Mappers.getMapper(IqcLineConvert.class);

    IqcLineDO convert(IqcLineCreateReqVO bean);

    IqcLineDO convert(IqcLineUpdateReqVO bean);

    IqcLineRespVO convert(IqcLineDO bean);

    List<IqcLineRespVO> convertList(List<IqcLineDO> list);

    PageResult<IqcLineRespVO> convertPage(PageResult<IqcLineDO> page);

    List<IqcLineExcelVO> convertList02(List<IqcLineDO> list);

}
