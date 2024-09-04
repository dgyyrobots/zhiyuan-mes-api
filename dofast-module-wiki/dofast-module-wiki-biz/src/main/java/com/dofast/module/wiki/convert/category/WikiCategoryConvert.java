package com.dofast.module.wiki.convert.category;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.dofast.module.wiki.controller.admin.category.vo.*;
import com.dofast.module.wiki.dal.dataobject.category.WikiCategoryDO;

/**
 * 首页的分类 Convert
 *
 * @author 惠智造
 */
@Mapper
public interface WikiCategoryConvert {

    WikiCategoryConvert INSTANCE = Mappers.getMapper(WikiCategoryConvert.class);

    WikiCategoryDO convert(WikiCategoryCreateReqVO bean);

    WikiCategoryDO convert(WikiCategoryUpdateReqVO bean);

    WikiCategoryRespVO convert(WikiCategoryDO bean);

    List<WikiCategoryRespVO> convertList(List<WikiCategoryDO> list);

    PageResult<WikiCategoryRespVO> convertPage(PageResult<WikiCategoryDO> page);

    List<WikiCategoryExcelVO> convertList02(List<WikiCategoryDO> list);

}
