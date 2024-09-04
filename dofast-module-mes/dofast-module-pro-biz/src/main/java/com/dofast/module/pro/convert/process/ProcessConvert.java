package com.dofast.module.pro.convert.process;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;

import com.dofast.module.pro.api.ProcessApi.dto.ProcessDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.dofast.module.pro.controller.admin.process.vo.*;
import com.dofast.module.pro.dal.dataobject.process.ProcessDO;

/**
 * 生产工序 Convert
 *
 * @author 芋道源码
 */
@Mapper
public interface ProcessConvert {

    ProcessConvert INSTANCE = Mappers.getMapper(ProcessConvert.class);

    ProcessDO convert(ProcessCreateReqVO bean);

    ProcessDO convert(ProcessUpdateReqVO bean);

    ProcessRespVO convert(ProcessDO bean);
    ProcessDTO convert01(ProcessDO bean);

    List<ProcessRespVO> convertList(List<ProcessDO> list);

    PageResult<ProcessRespVO> convertPage(PageResult<ProcessDO> page);

    List<ProcessExcelVO> convertList02(List<ProcessDO> list);

}
