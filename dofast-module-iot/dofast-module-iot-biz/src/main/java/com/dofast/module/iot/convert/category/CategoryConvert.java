package com.dofast.module.iot.convert.category;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.dofast.module.iot.controller.admin.category.vo.*;
import com.dofast.module.iot.dal.dataobject.category.CategoryDO;

/**
 * 产品分类 Convert
 *
 * @author 惠智造
 */
@Mapper
public interface CategoryConvert {

    CategoryConvert INSTANCE = Mappers.getMapper(CategoryConvert.class);

    CategoryDO convert(CategoryCreateReqVO bean);

    CategoryDO convert(CategoryUpdateReqVO bean);

    CategoryRespVO convert(CategoryDO bean);

    List<CategoryRespVO> convertList(List<CategoryDO> list);

    PageResult<CategoryRespVO> convertPage(PageResult<CategoryDO> page);

    List<CategoryExcelVO> convertList02(List<CategoryDO> list);

}
