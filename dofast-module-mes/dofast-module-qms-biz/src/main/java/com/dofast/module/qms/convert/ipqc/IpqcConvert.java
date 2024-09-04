package com.dofast.module.qms.convert.ipqc;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.dofast.module.qms.controller.admin.ipqc.vo.*;
import com.dofast.module.qms.dal.dataobject.ipqc.IpqcDO;

/**
 * 过程检验单 Convert
 *
 * @author 芋道源码
 */
@Mapper
public interface IpqcConvert {

    IpqcConvert INSTANCE = Mappers.getMapper(IpqcConvert.class);

    IpqcDO convert(IpqcCreateReqVO bean);

    IpqcDO convert(IpqcUpdateReqVO bean);

    IpqcRespVO convert(IpqcDO bean);

    List<IpqcRespVO> convertList(List<IpqcDO> list);

    PageResult<IpqcRespVO> convertPage(PageResult<IpqcDO> page);

    List<IpqcExcelVO> convertList02(List<IpqcDO> list);

}
