package com.dofast.module.qms.convert.templateindex;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.dofast.module.qms.controller.admin.templateindex.vo.*;
import com.dofast.module.qms.dal.dataobject.templateindex.TemplateIndexDO;

/**
 * 检测模板-检测项 Convert
 *
 * @author 芋道源码
 */
@Mapper
public interface TemplateIndexConvert {

    TemplateIndexConvert INSTANCE = Mappers.getMapper(TemplateIndexConvert.class);

    TemplateIndexDO convert(TemplateIndexCreateReqVO bean);

    TemplateIndexDO convert(TemplateIndexUpdateReqVO bean);

    TemplateIndexRespVO convert(TemplateIndexDO bean);

    List<TemplateIndexRespVO> convertList(List<TemplateIndexDO> list);

    PageResult<TemplateIndexRespVO> convertPage(PageResult<TemplateIndexDO> page);

    List<TemplateIndexExcelVO> convertList02(List<TemplateIndexDO> list);

}
