package com.dofast.module.pro.convert.workorderbom;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.dofast.module.pro.controller.admin.workorderbom.vo.*;
import com.dofast.module.pro.dal.dataobject.workorderbom.WorkorderBomDO;

/**
 * 生产工单BOM组成 Convert
 *
 * @author 芋道源码
 */
@Mapper
public interface WorkorderBomConvert {

    WorkorderBomConvert INSTANCE = Mappers.getMapper(WorkorderBomConvert.class);

    WorkorderBomDO convert(WorkorderBomCreateReqVO bean);

    WorkorderBomDO convert(WorkorderBomUpdateReqVO bean);

    WorkorderBomRespVO convert(WorkorderBomDO bean);

    List<WorkorderBomRespVO> convertList(List<WorkorderBomDO> list);

    PageResult<WorkorderBomRespVO> convertPage(PageResult<WorkorderBomDO> page);

    List<WorkorderBomExcelVO> convertList02(List<WorkorderBomDO> list);

}
