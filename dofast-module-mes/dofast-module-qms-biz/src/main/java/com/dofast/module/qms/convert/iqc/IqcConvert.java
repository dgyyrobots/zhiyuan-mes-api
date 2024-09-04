package com.dofast.module.qms.convert.iqc;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.dofast.module.qms.controller.admin.iqc.vo.*;
import com.dofast.module.qms.dal.dataobject.iqc.IqcDO;

/**
 * 来料检验单 Convert
 *
 * @author 芋道源码
 */
@Mapper
public interface IqcConvert {

    IqcConvert INSTANCE = Mappers.getMapper(IqcConvert.class);

    IqcDO convert(IqcCreateReqVO bean);

    IqcDO convert(IqcUpdateReqVO bean);

    IqcRespVO convert(IqcDO bean);

    List<IqcRespVO> convertList(List<IqcDO> list);

    PageResult<IqcRespVO> convertPage(PageResult<IqcDO> page);

    List<IqcExcelVO> convertList02(List<IqcDO> list);

}
