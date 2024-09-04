package com.dofast.module.pro.convert.workrecord;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.dofast.module.pro.controller.admin.workrecord.vo.*;
import com.dofast.module.pro.dal.dataobject.workrecord.WorkrecordDO;

/**
 * 上下工记录 Convert
 *
 * @author 惠智造
 */
@Mapper
public interface WorkrecordConvert {

    WorkrecordConvert INSTANCE = Mappers.getMapper(WorkrecordConvert.class);

    WorkrecordDO convert(WorkrecordCreateReqVO bean);

    WorkrecordDO convert(WorkrecordUpdateReqVO bean);

    WorkrecordRespVO convert(WorkrecordDO bean);

    List<WorkrecordRespVO> convertList(List<WorkrecordDO> list);

    PageResult<WorkrecordRespVO> convertPage(PageResult<WorkrecordDO> page);

    List<WorkrecordExcelVO> convertList02(List<WorkrecordDO> list);

}
