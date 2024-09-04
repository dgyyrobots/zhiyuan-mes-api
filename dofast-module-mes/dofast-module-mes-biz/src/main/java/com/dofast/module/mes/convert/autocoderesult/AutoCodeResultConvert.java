package com.dofast.module.mes.convert.autocoderesult;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.dofast.module.mes.controller.admin.autocoderesult.vo.*;
import com.dofast.module.mes.dal.dataobject.autocoderesult.AutoCodeResultDO;

/**
 * 编码生成记录 Convert
 *
 * @author 芋道源码
 */
@Mapper
public interface AutoCodeResultConvert {

    AutoCodeResultConvert INSTANCE = Mappers.getMapper(AutoCodeResultConvert.class);

    AutoCodeResultDO convert(AutoCodeResultCreateReqVO bean);

    AutoCodeResultDO convert(AutoCodeResultUpdateReqVO bean);

    AutoCodeResultRespVO convert(AutoCodeResultDO bean);

    List<AutoCodeResultRespVO> convertList(List<AutoCodeResultDO> list);

    PageResult<AutoCodeResultRespVO> convertPage(PageResult<AutoCodeResultDO> page);

    List<AutoCodeResultExcelVO> convertList02(List<AutoCodeResultDO> list);

}
