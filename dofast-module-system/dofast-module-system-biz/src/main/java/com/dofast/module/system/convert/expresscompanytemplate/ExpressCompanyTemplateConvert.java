package com.dofast.module.system.convert.expresscompanytemplate;

import com.dofast.framework.common.pojo.PageResult;
import com.dofast.module.system.controller.admin.expresscompanytemplate.vo.ExpressCompanyTemplateCreateReqVO;
import com.dofast.module.system.controller.admin.expresscompanytemplate.vo.ExpressCompanyTemplateExcelVO;
import com.dofast.module.system.controller.admin.expresscompanytemplate.vo.ExpressCompanyTemplateRespVO;
import com.dofast.module.system.controller.admin.expresscompanytemplate.vo.ExpressCompanyTemplateUpdateReqVO;
import com.dofast.module.system.dal.dataobject.expresscompanytemplate.ExpressCompanyTemplateDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 系统物流公司 Convert
 *
 * @author 惠智造
 */
@Mapper
public interface ExpressCompanyTemplateConvert {

    ExpressCompanyTemplateConvert INSTANCE = Mappers.getMapper(ExpressCompanyTemplateConvert.class);

    ExpressCompanyTemplateDO convert(ExpressCompanyTemplateCreateReqVO bean);

    ExpressCompanyTemplateDO convert(ExpressCompanyTemplateUpdateReqVO bean);

    ExpressCompanyTemplateRespVO convert(ExpressCompanyTemplateDO bean);

    List<ExpressCompanyTemplateRespVO> convertList(List<ExpressCompanyTemplateDO> list);

    PageResult<ExpressCompanyTemplateRespVO> convertPage(PageResult<ExpressCompanyTemplateDO> page);

    List<ExpressCompanyTemplateExcelVO> convertList02(List<ExpressCompanyTemplateDO> list);

}
