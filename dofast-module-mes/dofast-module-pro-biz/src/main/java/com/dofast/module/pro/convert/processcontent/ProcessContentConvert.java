package com.dofast.module.pro.convert.processcontent;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.dofast.module.pro.controller.admin.processcontent.vo.*;
import com.dofast.module.pro.dal.dataobject.processcontent.ProcessContentDO;

/**
 * 生产工序内容 Convert
 *
 * @author 芋道源码
 */
@Mapper
public interface ProcessContentConvert {

    ProcessContentConvert INSTANCE = Mappers.getMapper(ProcessContentConvert.class);

    ProcessContentDO convert(ProcessContentCreateReqVO bean);

    ProcessContentDO convert(ProcessContentUpdateReqVO bean);

    ProcessContentRespVO convert(ProcessContentDO bean);

    List<ProcessContentRespVO> convertList(List<ProcessContentDO> list);

    PageResult<ProcessContentRespVO> convertPage(PageResult<ProcessContentDO> page);

    List<ProcessContentExcelVO> convertList02(List<ProcessContentDO> list);

}
