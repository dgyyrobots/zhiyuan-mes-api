package com.dofast.module.cmms.convert.dvchecksubject;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.dofast.module.cmms.controller.admin.dvchecksubject.vo.*;
import com.dofast.module.cmms.dal.dataobject.dvchecksubject.DvCheckSubjectDO;

/**
 * 点检项目 Convert
 *
 * @author 芋道源码
 */
@Mapper
public interface DvCheckSubjectConvert {

    DvCheckSubjectConvert INSTANCE = Mappers.getMapper(DvCheckSubjectConvert.class);

    DvCheckSubjectDO convert(DvCheckSubjectCreateReqVO bean);

    DvCheckSubjectDO convert(DvCheckSubjectUpdateReqVO bean);

    DvCheckSubjectRespVO convert(DvCheckSubjectDO bean);

    List<DvCheckSubjectRespVO> convertList(List<DvCheckSubjectDO> list);

    PageResult<DvCheckSubjectRespVO> convertPage(PageResult<DvCheckSubjectDO> page);

    List<DvCheckSubjectExcelVO> convertList02(List<DvCheckSubjectDO> list);

}
