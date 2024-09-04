package com.dofast.module.finance.convert.subjectrelated;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.dofast.module.finance.controller.admin.subjectrelated.vo.*;
import com.dofast.module.finance.dal.dataobject.subjectrelated.SubjectRelatedDO;

/**
 * 收支科目 Convert
 *
 * @author a1
 */
@Mapper
public interface SubjectRelatedConvert {

    SubjectRelatedConvert INSTANCE = Mappers.getMapper(SubjectRelatedConvert.class);

    SubjectRelatedDO convert(SubjectRelatedCreateReqVO bean);

    SubjectRelatedDO convert(SubjectRelatedUpdateReqVO bean);

    SubjectRelatedRespVO convert(SubjectRelatedDO bean);

    List<SubjectRelatedRespVO> convertList(List<SubjectRelatedDO> list);

    PageResult<SubjectRelatedRespVO> convertPage(PageResult<SubjectRelatedDO> page);

    List<SubjectRelatedExcelVO> convertList02(List<SubjectRelatedDO> list);

}
