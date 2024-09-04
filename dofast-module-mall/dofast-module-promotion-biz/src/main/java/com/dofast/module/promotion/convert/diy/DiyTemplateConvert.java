package com.dofast.module.promotion.convert.diy;

import com.dofast.framework.common.pojo.PageResult;
import com.dofast.module.promotion.controller.admin.diy.vo.template.*;
import com.dofast.module.promotion.dal.dataobject.diy.DiyPageDO;
import com.dofast.module.promotion.dal.dataobject.diy.DiyTemplatesDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 装修模板 Convert
 *
 * @author owen
 */
@Mapper
public interface DiyTemplateConvert {

    DiyTemplateConvert INSTANCE = Mappers.getMapper(DiyTemplateConvert.class);

    DiyTemplatesDO convert(DiyTemplateCreateReqVO bean);

    DiyTemplatesDO convert(DiyTemplateUpdateReqVO bean);

    DiyTemplateRespVO convert(DiyTemplatesDO bean);

    List<DiyTemplateRespVO> convertList(List<DiyTemplatesDO> list);

    PageResult<DiyTemplateRespVO> convertPage(PageResult<DiyTemplatesDO> page);

    DiyTemplatePropertyRespVO convertPropertyVo(DiyTemplatesDO diyTemplate, List<DiyPageDO> pages);

    DiyTemplatesDO convert(DiyTemplatePropertyUpdateRequestVO updateReqVO);


}
