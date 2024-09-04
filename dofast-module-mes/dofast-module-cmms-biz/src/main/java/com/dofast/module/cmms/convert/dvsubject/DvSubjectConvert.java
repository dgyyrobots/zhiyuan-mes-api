package com.dofast.module.cmms.convert.dvsubject;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.dofast.module.cmms.controller.admin.dvsubject.vo.*;
import com.dofast.module.cmms.dal.dataobject.dvsubject.DvSubjectDO;

/**
 * 设备点检保养项目 Convert
 *
 * @author 芋道源码
 */
@Mapper
public interface DvSubjectConvert {

    DvSubjectConvert INSTANCE = Mappers.getMapper(DvSubjectConvert.class);

    DvSubjectDO convert(DvSubjectCreateReqVO bean);

    DvSubjectDO convert(DvSubjectUpdateReqVO bean);

    DvSubjectRespVO convert(DvSubjectDO bean);

    List<DvSubjectRespVO> convertList(List<DvSubjectDO> list);

    PageResult<DvSubjectRespVO> convertPage(PageResult<DvSubjectDO> page);

    List<DvSubjectExcelVO> convertList02(List<DvSubjectDO> list);

}
