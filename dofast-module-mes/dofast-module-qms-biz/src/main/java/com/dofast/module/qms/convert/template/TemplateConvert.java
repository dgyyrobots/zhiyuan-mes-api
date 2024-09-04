package com.dofast.module.qms.convert.template;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.dofast.module.qms.controller.admin.template.vo.*;
import com.dofast.module.qms.dal.dataobject.template.TemplateDO;

/**
 * 检测模板 Convert
 *
 * @author 芋道源码
 */
@Mapper
public interface TemplateConvert {

    TemplateConvert INSTANCE = Mappers.getMapper(TemplateConvert.class);

    TemplateDO convert(TemplateCreateReqVO bean);

    TemplateDO convert(TemplateUpdateReqVO bean);

    TemplateRespVO convert(TemplateDO bean);

    List<TemplateRespVO> convertList(List<TemplateDO> list);

    PageResult<TemplateRespVO> convertPage(PageResult<TemplateDO> page);

    List<TemplateExcelVO> convertList02(List<TemplateDO> list);

}
