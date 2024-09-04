package com.dofast.module.system.convert.expresstemplateitem;

import com.dofast.framework.common.pojo.PageResult;
import com.dofast.module.system.controller.admin.expresstemplateitem.vo.ExpressTemplateItemCreateReqVO;
import com.dofast.module.system.controller.admin.expresstemplateitem.vo.ExpressTemplateItemExcelVO;
import com.dofast.module.system.controller.admin.expresstemplateitem.vo.ExpressTemplateItemRespVO;
import com.dofast.module.system.controller.admin.expresstemplateitem.vo.ExpressTemplateItemUpdateReqVO;
import com.dofast.module.system.dal.dataobject.expresstemplateitem.ExpressTemplateItemDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 运费模板细节 Convert
 *
 * @author 惠智造
 */
@Mapper
public interface ExpressTemplateItemConvert {

    ExpressTemplateItemConvert INSTANCE = Mappers.getMapper(ExpressTemplateItemConvert.class);

    ExpressTemplateItemDO convert(ExpressTemplateItemCreateReqVO bean);

    ExpressTemplateItemDO convert(ExpressTemplateItemUpdateReqVO bean);

    ExpressTemplateItemRespVO convert(ExpressTemplateItemDO bean);

    List<ExpressTemplateItemRespVO> convertList(List<ExpressTemplateItemDO> list);

    PageResult<ExpressTemplateItemRespVO> convertPage(PageResult<ExpressTemplateItemDO> page);

    List<ExpressTemplateItemExcelVO> convertList02(List<ExpressTemplateItemDO> list);

}
