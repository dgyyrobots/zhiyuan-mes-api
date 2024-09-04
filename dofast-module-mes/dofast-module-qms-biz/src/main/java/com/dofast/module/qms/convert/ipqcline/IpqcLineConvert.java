package com.dofast.module.qms.convert.ipqcline;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.dofast.module.qms.controller.admin.ipqcline.vo.*;
import com.dofast.module.qms.dal.dataobject.ipqcline.IpqcLineDO;

/**
 * 过程检验单行 Convert
 *
 * @author 芋道源码
 */
@Mapper
public interface IpqcLineConvert {

    IpqcLineConvert INSTANCE = Mappers.getMapper(IpqcLineConvert.class);

    IpqcLineDO convert(IpqcLineCreateReqVO bean);

    IpqcLineDO convert(IpqcLineUpdateReqVO bean);

    IpqcLineRespVO convert(IpqcLineDO bean);

    List<IpqcLineRespVO> convertList(List<IpqcLineDO> list);

    PageResult<IpqcLineRespVO> convertPage(PageResult<IpqcLineDO> page);

    List<IpqcLineExcelVO> convertList02(List<IpqcLineDO> list);

}
