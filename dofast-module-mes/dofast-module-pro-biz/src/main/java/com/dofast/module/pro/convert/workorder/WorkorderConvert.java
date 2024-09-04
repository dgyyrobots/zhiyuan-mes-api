package com.dofast.module.pro.convert.workorder;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;

import com.dofast.module.pro.api.WorkorderApi.dto.WorkorderDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.dofast.module.pro.controller.admin.workorder.vo.*;
import com.dofast.module.pro.dal.dataobject.workorder.WorkorderDO;

/**
 * 生产工单 Convert
 *
 * @author 芋道源码
 */
@Mapper
public interface WorkorderConvert {

    WorkorderConvert INSTANCE = Mappers.getMapper(WorkorderConvert.class);

    WorkorderDO convert(WorkorderCreateReqVO bean);

    WorkorderUpdateReqVO convert1(WorkorderDO bean);

    WorkorderDO convert(WorkorderUpdateReqVO bean);

    WorkorderRespVO convert(WorkorderDO bean);
    WorkorderDTO convert01(WorkorderDO bean);

    List<WorkorderRespVO> convertList(List<WorkorderDO> list);

    PageResult<WorkorderRespVO> convertPage(PageResult<WorkorderDO> page);

    List<WorkorderExcelVO> convertList02(List<WorkorderDO> list);

}
