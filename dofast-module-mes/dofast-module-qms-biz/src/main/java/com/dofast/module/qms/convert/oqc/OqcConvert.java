package com.dofast.module.qms.convert.oqc;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.dofast.module.qms.controller.admin.oqc.vo.*;
import com.dofast.module.qms.dal.dataobject.oqc.OqcDO;

/**
 * 出货检验单 Convert
 *
 * @author 芋道源码
 */
@Mapper
public interface OqcConvert {

    OqcConvert INSTANCE = Mappers.getMapper(OqcConvert.class);

    OqcDO convert(OqcCreateReqVO bean);

    OqcDO convert(OqcUpdateReqVO bean);

    OqcRespVO convert(OqcDO bean);

    List<OqcRespVO> convertList(List<OqcDO> list);

    PageResult<OqcRespVO> convertPage(PageResult<OqcDO> page);

    List<OqcExcelVO> convertList02(List<OqcDO> list);

}
