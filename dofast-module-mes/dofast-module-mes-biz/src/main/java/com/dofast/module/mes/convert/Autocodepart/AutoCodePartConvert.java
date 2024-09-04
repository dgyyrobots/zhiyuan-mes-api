package com.dofast.module.mes.convert.Autocodepart;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.dofast.module.mes.controller.admin.Autocodepart.vo.*;
import com.dofast.module.mes.dal.dataobject.Autocodepart.AutoCodePartDO;

/**
 * 编码生成规则组成 Convert
 *
 * @author 芋道源码
 */
@Mapper
public interface AutoCodePartConvert {

    AutoCodePartConvert INSTANCE = Mappers.getMapper(AutoCodePartConvert.class);

    AutoCodePartDO convert(AutoCodePartCreateReqVO bean);

    AutoCodePartDO convert(AutoCodePartUpdateReqVO bean);

    AutoCodePartRespVO convert(AutoCodePartDO bean);

    List<AutoCodePartRespVO> convertList(List<AutoCodePartDO> list);

    PageResult<AutoCodePartRespVO> convertPage(PageResult<AutoCodePartDO> page);

    List<AutoCodePartExcelVO> convertList02(List<AutoCodePartDO> list);

}
