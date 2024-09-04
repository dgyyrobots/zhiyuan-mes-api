package com.dofast.module.cmms.convert.dvcheckplan;

import java.util.*;

import com.dofast.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.dofast.module.cmms.controller.admin.dvcheckplan.vo.*;
import com.dofast.module.cmms.dal.dataobject.dvcheckplan.DvCheckPlanDO;

/**
 * 设备点检保养计划头 Convert
 *
 * @author 芋道源码
 */
@Mapper
public interface DvCheckPlanConvert {

    DvCheckPlanConvert INSTANCE = Mappers.getMapper(DvCheckPlanConvert.class);

    DvCheckPlanDO convert(DvCheckPlanCreateReqVO bean);

    DvCheckPlanDO convert(DvCheckPlanUpdateReqVO bean);

    DvCheckPlanRespVO convert(DvCheckPlanDO bean);

    List<DvCheckPlanRespVO> convertList(List<DvCheckPlanDO> list);

    PageResult<DvCheckPlanRespVO> convertPage(PageResult<DvCheckPlanDO> page);

    List<DvCheckPlanExcelVO> convertList02(List<DvCheckPlanDO> list);

}
