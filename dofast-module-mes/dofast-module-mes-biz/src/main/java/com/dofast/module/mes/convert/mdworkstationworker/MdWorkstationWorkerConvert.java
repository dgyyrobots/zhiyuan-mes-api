package com.dofast.module.mes.convert.mdworkstationworker;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.dofast.module.mes.controller.admin.mdworkstationworker.vo.*;
import com.dofast.module.mes.dal.dataobject.mdworkstationworker.MdWorkstationWorkerDO;

/**
 * 人力资源 Convert
 *
 * @author 芋道源码
 */
@Mapper
public interface MdWorkstationWorkerConvert {

    MdWorkstationWorkerConvert INSTANCE = Mappers.getMapper(MdWorkstationWorkerConvert.class);

    MdWorkstationWorkerDO convert(MdWorkstationWorkerCreateReqVO bean);

    MdWorkstationWorkerDO convert(MdWorkstationWorkerUpdateReqVO bean);

    MdWorkstationWorkerRespVO convert(MdWorkstationWorkerDO bean);

    List<MdWorkstationWorkerRespVO> convertList(List<MdWorkstationWorkerDO> list);

    PageResult<MdWorkstationWorkerRespVO> convertPage(PageResult<MdWorkstationWorkerDO> page);

    List<MdWorkstationWorkerExcelVO> convertList02(List<MdWorkstationWorkerDO> list);

}
