package com.dofast.module.iot.convert.thingsmodeltemplate;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.dofast.module.iot.controller.admin.thingsmodeltemplate.vo.*;
import com.dofast.module.iot.dal.dataobject.thingsmodeltemplate.ThingsModelTemplateDO;

/**
 * 物模型模板 Convert
 *
 * @author 惠智造
 */
@Mapper
public interface ThingsModelTemplateConvert {

    ThingsModelTemplateConvert INSTANCE = Mappers.getMapper(ThingsModelTemplateConvert.class);

    ThingsModelTemplateDO convert(ThingsModelTemplateCreateReqVO bean);

    ThingsModelTemplateDO convert(ThingsModelTemplateUpdateReqVO bean);

    ThingsModelTemplateRespVO convert(ThingsModelTemplateDO bean);

    List<ThingsModelTemplateRespVO> convertList(List<ThingsModelTemplateDO> list);

    PageResult<ThingsModelTemplateRespVO> convertPage(PageResult<ThingsModelTemplateDO> page);

    List<ThingsModelTemplateExcelVO> convertList02(List<ThingsModelTemplateDO> list);

}
