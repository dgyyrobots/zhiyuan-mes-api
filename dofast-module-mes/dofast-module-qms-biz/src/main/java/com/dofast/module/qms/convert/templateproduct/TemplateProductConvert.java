package com.dofast.module.qms.convert.templateproduct;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.dofast.module.qms.controller.admin.templateproduct.vo.*;
import com.dofast.module.qms.dal.dataobject.templateproduct.TemplateProductDO;

/**
 * 检测模板-产品 Convert
 *
 * @author 芋道源码
 */
@Mapper
public interface TemplateProductConvert {

    TemplateProductConvert INSTANCE = Mappers.getMapper(TemplateProductConvert.class);

    TemplateProductDO convert(TemplateProductCreateReqVO bean);

    TemplateProductDO convert(TemplateProductUpdateReqVO bean);

    TemplateProductRespVO convert(TemplateProductDO bean);

    List<TemplateProductRespVO> convertList(List<TemplateProductDO> list);

    PageResult<TemplateProductRespVO> convertPage(PageResult<TemplateProductDO> page);

    List<TemplateProductExcelVO> convertList02(List<TemplateProductDO> list);

}
