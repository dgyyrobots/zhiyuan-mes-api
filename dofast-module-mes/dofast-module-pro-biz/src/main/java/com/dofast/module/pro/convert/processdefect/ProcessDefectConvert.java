package com.dofast.module.pro.convert.processdefect;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.dofast.module.pro.controller.admin.processdefect.vo.*;
import com.dofast.module.pro.dal.dataobject.processdefect.ProcessDefectDO;

/**
 * 工序异常缺陷名称 Convert
 *
 * @author 惠智造
 */
@Mapper
public interface ProcessDefectConvert {

    ProcessDefectConvert INSTANCE = Mappers.getMapper(ProcessDefectConvert.class);

    ProcessDefectDO convert(ProcessDefectCreateReqVO bean);

    ProcessDefectDO convert(ProcessDefectUpdateReqVO bean);

    ProcessDefectRespVO convert(ProcessDefectDO bean);

    List<ProcessDefectRespVO> convertList(List<ProcessDefectDO> list);

    PageResult<ProcessDefectRespVO> convertPage(PageResult<ProcessDefectDO> page);

    List<ProcessDefectExcelVO> convertList02(List<ProcessDefectDO> list);

}
