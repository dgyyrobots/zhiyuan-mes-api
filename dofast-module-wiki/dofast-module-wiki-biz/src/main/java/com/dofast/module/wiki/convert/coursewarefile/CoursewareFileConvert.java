package com.dofast.module.wiki.convert.coursewarefile;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.dofast.module.wiki.controller.admin.coursewarefile.vo.*;
import com.dofast.module.wiki.dal.dataobject.coursewarefile.CoursewareFileDO;

/**
 * 课件文件的保存地址 Convert
 *
 * @author 惠智造
 */
@Mapper
public interface CoursewareFileConvert {

    CoursewareFileConvert INSTANCE = Mappers.getMapper(CoursewareFileConvert.class);

    CoursewareFileDO convert(CoursewareFileCreateReqVO bean);

    CoursewareFileDO convert(CoursewareFileUpdateReqVO bean);

    CoursewareFileRespVO convert(CoursewareFileDO bean);

    List<CoursewareFileRespVO> convertList(List<CoursewareFileDO> list);

    PageResult<CoursewareFileRespVO> convertPage(PageResult<CoursewareFileDO> page);

    List<CoursewareFileExcelVO> convertList02(List<CoursewareFileDO> list);

}
