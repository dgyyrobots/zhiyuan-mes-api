package com.dofast.module.system.convert.expresstemplate;

import com.dofast.framework.common.pojo.PageResult;
import com.dofast.module.system.controller.admin.expresstemplate.vo.ExpressTemplateCreateReqVO;
import com.dofast.module.system.controller.admin.expresstemplate.vo.ExpressTemplateExcelVO;
import com.dofast.module.system.controller.admin.expresstemplate.vo.ExpressTemplateRespVO;
import com.dofast.module.system.controller.admin.expresstemplate.vo.ExpressTemplateUpdateReqVO;
import com.dofast.module.system.dal.dataobject.expresstemplate.ExpressTemplateDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 运费模板 Convert
 *
 * @author 惠智造
 */
@Mapper
public interface ExpressTemplateConvert {

    ExpressTemplateConvert INSTANCE = Mappers.getMapper(ExpressTemplateConvert.class);

    ExpressTemplateDO convert(ExpressTemplateCreateReqVO bean);

    ExpressTemplateDO convert(ExpressTemplateUpdateReqVO bean);

    ExpressTemplateRespVO convert(ExpressTemplateDO bean);

    List<ExpressTemplateRespVO> convertList(List<ExpressTemplateDO> list);

    PageResult<ExpressTemplateRespVO> convertPage(PageResult<ExpressTemplateDO> page);

    List<ExpressTemplateExcelVO> convertList02(List<ExpressTemplateDO> list);

}
