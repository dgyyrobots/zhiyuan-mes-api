package com.dofast.module.wiki.convert.lecturer;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.dofast.module.wiki.controller.admin.lecturer.vo.*;
import com.dofast.module.wiki.dal.dataobject.lecturer.WikiLecturerDO;

/**
 * 讲师的信息	 Convert
 *
 * @author 惠智造
 */
@Mapper
public interface WikiLecturerConvert {

    WikiLecturerConvert INSTANCE = Mappers.getMapper(WikiLecturerConvert.class);

    WikiLecturerDO convert(WikiLecturerCreateReqVO bean);

    WikiLecturerDO convert(WikiLecturerUpdateReqVO bean);

    WikiLecturerRespVO convert(WikiLecturerDO bean);

    List<WikiLecturerRespVO> convertList(List<WikiLecturerDO> list);

    PageResult<WikiLecturerRespVO> convertPage(PageResult<WikiLecturerDO> page);

    List<WikiLecturerExcelVO> convertList02(List<WikiLecturerDO> list);

}
