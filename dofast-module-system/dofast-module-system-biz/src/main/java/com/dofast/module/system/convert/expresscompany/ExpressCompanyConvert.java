package com.dofast.module.system.convert.expresscompany;

import com.dofast.framework.common.pojo.PageResult;
import com.dofast.module.system.controller.admin.expresscompany.vo.ExpressCompanyCreateReqVO;
import com.dofast.module.system.controller.admin.expresscompany.vo.ExpressCompanyExcelVO;
import com.dofast.module.system.controller.admin.expresscompany.vo.ExpressCompanyRespVO;
import com.dofast.module.system.controller.admin.expresscompany.vo.ExpressCompanyUpdateReqVO;
import com.dofast.module.system.dal.dataobject.expresscompany.ExpressCompanyDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 租户物流公司 Convert
 *
 * @author 惠智造
 */
@Mapper
public interface ExpressCompanyConvert {

    ExpressCompanyConvert INSTANCE = Mappers.getMapper(ExpressCompanyConvert.class);

    ExpressCompanyDO convert(ExpressCompanyCreateReqVO bean);

    ExpressCompanyDO convert(ExpressCompanyUpdateReqVO bean);

    ExpressCompanyRespVO convert(ExpressCompanyDO bean);

    List<ExpressCompanyRespVO> convertList(List<ExpressCompanyDO> list);

    PageResult<ExpressCompanyRespVO> convertPage(PageResult<ExpressCompanyDO> page);

    List<ExpressCompanyExcelVO> convertList02(List<ExpressCompanyDO> list);

}
