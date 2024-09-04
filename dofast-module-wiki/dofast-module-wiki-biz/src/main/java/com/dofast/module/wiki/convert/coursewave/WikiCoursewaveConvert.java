package com.dofast.module.wiki.convert.coursewave;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.dofast.module.wiki.controller.admin.coursewave.vo.*;
import com.dofast.module.wiki.dal.dataobject.coursewave.WikiCoursewaveDO;

/**
 * 首页知识列表的信息	 Convert
 *
 * @author 惠智造
 */
@Mapper
public interface WikiCoursewaveConvert {

    WikiCoursewaveConvert INSTANCE = Mappers.getMapper(WikiCoursewaveConvert.class);

    WikiCoursewaveDO convert(WikiCoursewaveCreateReqVO bean);

    WikiCoursewaveDO convert(WikiCoursewaveUpdateReqVO bean);

    WikiCoursewaveRespVO convert(WikiCoursewaveDO bean);

    List<WikiCoursewaveRespVO> convertList(List<WikiCoursewaveDO> list);

    PageResult<WikiCoursewaveRespVO> convertPage(PageResult<WikiCoursewaveDO> page);

    List<WikiCoursewaveExcelVO> convertList02(List<WikiCoursewaveDO> list);

}
